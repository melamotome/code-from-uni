#!/usr/bin/python

'''Написать функцию, на вход которой подается имя
текстового файла и список слов. Необходимо подсчитать
число вхождений каждого слова из списка в файле, а
также сумму всех вхождений.
'''
import string
from hw72Улучшенныйчастотныйсловарь.py import freq

words = ['вильнюс', 'волк', 'множество', 'нервы']
nameOfFile = "TheSameBook.txt"

def findWords(nameOfFile, words):
    with open(nameOfFile, 'r', encoding='utf8') as fin:
        lines = fin.readlines()

    d = {}
    for line in lines:
        line = line.strip(string.whitespace)
        line = line.lower()

        for word in line.split(' '):
            if len(word) > 2:
                cleanWord = word
                for chara in word:
                    if chara in string.punctuation:
                        cleanWord = word.replace(chara, "")
                if not word:
                    continue
                d[cleanWord] = d.get(cleanWord, 0) + 1

    s = 0
    for word in words:
        if word in d:
            print('Слово "{0}" входит в текст {1} раз'.format(word, d[word]))
            s += d[word]
        else:
            print('Слово "{0}" не входит в текст'.format(word))
    print('Сумма всех вхождений равна {0}'.format(s))

findWords(nameOfFile, words)







