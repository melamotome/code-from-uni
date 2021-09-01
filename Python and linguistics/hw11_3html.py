#!/usr/bin/python
''' Открыть страницу друзей «Вконтакте» любого
пользователя и сохранить ее HTML-код в файл.
Прочитать файл с помощью Python и вывести
идентификаторы друзей (в виде числа или
символьного имени) и URL-адрес их аватаров в файл CSV.'''

from bs4 import BeautifulSoup
import re

with open('vk_friends.html', 'r', encoding='utf8') as fin:
    html = fin.read()

soup = BeautifulSoup(html, 'html.parser')

picList = soup.find_all('img', class_ = "friends_photo_img")
pics = []
for currentPic in picList:
    print(currentPic)
    pics.append(currentPic['src'])

idList = soup.find_all('div', class_ = "friends_field friends_field_title")
ids = []
for currentId in idList:
    ids.append(currentId.find('a', href=True)['href'])

with open('vk_friends.csv', 'w', encoding='utf8') as fout:
    for pic, id in zip(pics, ids):
        fout.write('"{0}","{1}"\n'.format(pic, id))


