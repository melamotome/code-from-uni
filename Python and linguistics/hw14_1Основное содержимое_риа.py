#!/usr/bin/python

'''
 Загрузить основное содержимое любого
новостного ресурса в файл на диске
(requests+beautifulsoup)
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

#Это заготовлено для главной страницы риа новостей
def get_main_from_html(html, file_out):
    soup = bs(html, 'html.parser')
    with open(file_out, 'w', encoding='utf8') as fout:
        #Главный заголовок
        fout.write('{0}. {1}\n'.format(soup.find('div', class_ = "b-index__main-news-title").get_text(), soup.find('div', class_="b-index__main-news-announce").get_text()))
        #Панелька справа от заголовка
        current = soup.find('div', class_ = "b-index__main-list-place")
        for text in current.find_all('span'):
            fout.write('{0}.\n'.format(text.get_text()))
        #Читаемое
        current = soup.find_all('span', class_="b-index__popular-m-item-title")
        for text in current:
            span = text.find('span').get_text()
            if not span:
                continue
            else:
                fout.write('{0}.\n'.format(span))
        #Лента
        current = soup.find_all('span', class_="b-index__popular-item-title")
        for text in current:
            fout.write('{0}.\n'.format(text.get_text()))
        #Рекомендуем
        current = soup.find_all('span', class_="b-index__notice-title")
        for text in current:
            fout.write('{0}.\n'.format(text.get_text()))

get_main_from_html(get_html('https://ria.ru'), 'ria_main.txt')


