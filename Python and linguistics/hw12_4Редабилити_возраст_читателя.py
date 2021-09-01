#!/usr/bin/python

'''Написать функцию, которая принимает на вход имя
текстового файла и вычисляет метрику читабельности
текста (Readability) по формуле
0.39(total words/total sentences)+ 11.8(total syllables/total words) - 15.59'''

import string
import re
#"Одна и та же книга" Макса Фрая
with open('TheSameBook.txt', 'r', encoding='utf8') as fin:
    lines = fin.readlines()

TWords = 0
TSyllables = 0
TSentences = 0

wordPattern = re.compile('\w+')
sentencePattern = re.compile(' *[.?!]+ *')
syllablePattern = re.compile('[уеыаоэяиюeyuioa]')
for line in lines:
    #Получаю колво слов
    TSyllables = TSyllables + len(re.findall(syllablePattern,line))
    TWords = TWords + len(re.findall(wordPattern, line))

    # Делю на предложения строки
    sentences = re.split(sentencePattern, line)
    for i in range(0, len(sentences)):
        #Убираю переносы строки
        sentences[i] = sentences[i].replace('\n', '')
        #Считаю колво предложений
        if sentences[i] != '':
            TSentences += 1

print("Readability is {0:.3f}".format(0.39 * (TWords/TSentences)+ 11.8 * (TSyllables/TWords) - 15.59))
#На данный момент выведет Readability is 14.730
