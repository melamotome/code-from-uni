#!/usr/bin/python

'''
Загрузить и провести анализ тональности 5
статей с РБК и РИА Новости с похожими заголовками
'''

import os
import re
import random
import lec13.sent_analysis_full as ton
import pymorphy2

import urllib.error
import http
from http.cookiejar import LWPCookieJar

import requests
from bs4 import BeautifulSoup as bs


def get_html(link, encoding='UTF8'):
    """
    Grab some web page
    """

    # TEMP = 'temp'

    # Crawling parameters
    browsers = ['Mozilla/5.0 (X11; U; Linux i686) Gecko/20071127 Firefox/2.0.0.11',
                'Internet Explorer 6.0',
                'Mozilla/5.0 (Windows; U; Windows NT 5.1; it; rv:1.8.1.11) Gecko/20071127 Firefox/2.0.0.11',
                'Opera/9.25 (Windows NT 5.1; U; en)',
                'Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 1.1.4322; .NET CLR 2.0.50727)',
                'Mozilla/5.0 (compatible; Konqueror/3.5; Linux) KHTML/3.5.5 (like Gecko) (Kubuntu)',
                'Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.8.0.12) Gecko/20070731 Ubuntu/dapper-security Firefox/1.5.0.12',
                'Lynx/2.8.5rel.1 libwww-FM/2.14 SSL-MM/1.4.1 GNUTLS/1.2.9',
                'Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.111 Safari/537.36 OPR/25.0.1614.68']

    # cookies = os.path.join(TEMP, 'cookie')
    # if os.path.exists(cookies):
    #    os.remove(cookies)

    try:
        s = requests.Session()
        # s.cookies = LWPCookieJar(cookies)

        # if not os.path.exists(cookies):
        #     s.cookies.save()
        # else:
        #     s.cookies.load(ignore_discard=True)

        r = s.get(link, headers={'User-Agent': random.choice(browsers)})

        if encoding.upper().replace('-', '') != 'UTF8':
            r.encoding = encoding

        # s.cookies.save(ignore_discard=True)

        return r.text

    except urllib.error.HTTPError as e:
        print(e)
        return None
    except http.cookiejar.LoadError as e:
        print(e)
    except Exception as e:
        print(e)
        return None

#Статья рбк
def get_main_article_rbk(html, file_out):
    soup = bs(html, 'html.parser')
    with open(file_out, 'w', encoding='utf8') as fout:
        #Заголовок статьи
        title = soup.find('div', class_="article__header__title")
        if title:
            fout.write('{0}\n'.format(title.get_text()))
        #Овервью
        overview = soup.find('div', class_="article__text__overview")
        if overview:
            fout.write('{0}\n'.format(overview.get_text()))
        #Текст
        for current in soup.find_all('p'):
            if not current.find('script') and not current.find('div', class_="article__photoreport"):
                fout.write('{0}.\n'.format(current.get_text()))

#Статья риа
def get_main_article_ria(html, file_out):
    soup = bs(html, 'html.parser')
    with open(file_out, 'w', encoding='utf8') as fout:
        #Заголовок статьи
        title = soup.find('h1', class_="b-article__title")
        if title:
            fout.write('{0}\n'.format(title.get_text()))
        #Текст
        for current in soup.find_all('div', class_="b-article__body js-mediator-article mia-analytics"):
            for currentP in current.find_all('p'):
                if not currentP.find('script') and not currentP.find('div', class_="b-media-copyright__copy"):
                    fout.write('{0}.\n'.format(currentP.get_text()))


articles = [['https://ria.ru/world/20171124/1509522441.html?referrer_block=..','https://www.rbc.ru/society/24/11/2017/5a18074e9a794784d2935d6..'],
['https://ria.ru/economy/20171124/1509501842.html','https://www.rbc.ru/technology_and_media/24/11/2017/5a0d66189a..'],
['https://ria.ru/society/20171124/1509502710.html?referrer_bloc..','https://www.rbc.ru/rbcfreenews/5a180d769a79470458d63c3c'],
['https://ria.ru/world/20171124/1509534087.html','https://www.rbc.ru/rbcfreenews/5a1818369a79470f6745668e?from=..'],
['https://ria.ru/society/20171124/1509483960.html','https://www.rbc.ru/rbcfreenews/5a17cad89a79474d6049cbf4']]

for article in articles:
    file_in_ria = 'current_ria.txt'
    file_in_rbk = 'current_rbk.txt'
    get_main_article_ria(get_html(article[0]), file_in_ria)
    get_main_article_rbk(get_html(article[1]), file_in_rbk)                                                               

    file_lemma_ria = 'current_ria_lemma.txt'
    file_lemma_rbk = 'current_rbk_lemma.txt'

    sentim_file = 'sentiment.txt'
    sentim_weights_file = 'sentiment_weights.txt'

    morph = pymorphy2.MorphAnalyzer()
    ton.lemmatize(morph, file_in_ria, file_lemma_ria)
    ton.lemmatize(morph, file_in_rbk, file_lemma_rbk)

    sentim = ton.load_sentiment(sentim_weights_file)
    score_ria = ton.analyze(file_lemma_ria, sentim)
    score_rbk = ton.analyze(file_lemma_rbk, sentim)
    print('Тональность статьи риа {0}: {1:.4f}'.format(article[0], score_ria))
    print('Тональность статьи рбк {0}: {1:.4f}\n'.format(article[1], score_rbk))