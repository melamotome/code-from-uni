#!/usr/bin/python

'''
Написать скрипт, считывающий ТОП-5 заголовков
новостей любого новостного агентства и
загружающего их основное содержимое
'''

#!/usr/bin/python

import os
import re
import random

import urllib.error
import http
from http.cookiejar import LWPCookieJar

import requests
from bs4 import BeautifulSoup as bs


def get_html(link, encoding='UTF8'):
    browsers = ['Mozilla/5.0 (X11; U; Linux i686) Gecko/20071127 Firefox/2.0.0.11',
                'Internet Explorer 6.0',
                'Mozilla/5.0 (Windows; U; Windows NT 5.1; it; rv:1.8.1.11) Gecko/20071127 Firefox/2.0.0.11',
                'Opera/9.25 (Windows NT 5.1; U; en)',
                'Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 1.1.4322; .NET CLR 2.0.50727)',
                'Mozilla/5.0 (compatible; Konqueror/3.5; Linux) KHTML/3.5.5 (like Gecko) (Kubuntu)',
                'Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.8.0.12) Gecko/20070731 Ubuntu/dapper-security Firefox/1.5.0.12',
                'Lynx/2.8.5rel.1 libwww-FM/2.14 SSL-MM/1.4.1 GNUTLS/1.2.9',
                'Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.111 Safari/537.36 OPR/25.0.1614.68']

    try:
        s = requests.Session()
        r = s.get(link, headers={'User-Agent': random.choice(browsers)})

        if encoding.upper().replace('-', '') != 'UTF8':
            r.encoding = encoding
        return r.text

    except urllib.error.HTTPError as e:
        print(e)
        return None
    except http.cookiejar.LoadError as e:
        print(e)
    except Exception as e:
        print(e)
        return None

#Это заготовлено для главной страницы риа новостей, беру топ 5 статей
def get_top(adress, file_out):
    html = get_html(adress)
    soup = bs(html, 'html.parser')
    with open(file_out, 'w', encoding='utf8') as fout:
        #Главный заголовок
        url = soup.find('div', class_ = 'b-index__main-news').find('a', href=True)['href']
        fout.write(get_main_article_ria(get_html(adress + url)))

        #Панелька справа от заголовка
        current = soup.find('div', class_ = "b-index__main-list-place")
        for url in current.find_all('a', href=True):
            fout.write(get_main_article_ria(get_html(adress + url['href'])))

def get_main_article_ria(html):
    soup = bs(html, 'html.parser')
    TEXT = ""
    #Заголовок статьи
    title = soup.find('h1', class_="b-article__title")
    if title:
        TEXT = TEXT + '{0}\n'.format(title.get_text())
    #Текст
    for current in soup.find_all('div', class_="b-article__body js-mediator-article mia-analytics"):
        for currentP in current.find_all('p'):
            if currentP.get("class") == "vjs-no-js":
                continue
            if not currentP.find('script') and not currentP.find('div', class_="b-media-copyright__copy"):
                TEXT = TEXT + '{0}.\n'.format(currentP.get_text())

    #ПУТИН
    for current in soup.find_all('div', class_="b-article__body js-mediator-article mia-analytics"):
        for currentP in current.find_all('li'):
            TEXT = TEXT + '{0}.\n'.format(currentP.get_text())

    TEXT = TEXT + "\n"
    return TEXT


get_top('https://ria.ru', 'ria_top5.txt')


