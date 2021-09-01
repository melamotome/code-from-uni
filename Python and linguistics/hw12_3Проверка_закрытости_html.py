#!/usr/bin/python

'''Проверить корректность файла HTML по правилу «каждому
открывающему тэгу <tag> соответствует закрывающий тэг
</tag>».'''

import re

with open('test.html', 'r', encoding='utf8') as fin:
    html = fin.read()

patternOpen = re.compile('<[^/!>]+>')
#!, чтобы не брать доктайп. > чтобы брать именно один тег. / чтобы не брать непарные теги
patternClose = re.compile('</[^>]+>')

#Обработка комментариев, в которых могут быть теги(незакрытые тоже)
patternComment = re.compile('<!--.+-->')
html = re.sub(patternComment, '', html)

open = re.findall(patternOpen, html)
close = re.findall(patternClose, html)


print("Is every tag closed?")

print(len(open) == len(close))
