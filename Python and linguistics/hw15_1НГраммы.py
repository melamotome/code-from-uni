#!/usr/bin/python

'''
 Написать функцию, которая осуществляет
построение частотного словаря биграмм. В качестве
ключей словаря должны выступать пары слов вместе с
их частями речи. Вывести частотный словарь в файл,
отсортировав его по убыванию частот.
'''

import string
import pymorphy2
from collections import defaultdict
morph = pymorphy2.MorphAnalyzer()
freqs = defaultdict(int)

def extract_ngrams(words, n):
    ngrams = []
    for i in range(len(words) - n + 1):
        ngrams.append(' '.join(words[i:i+n]))
    return ngrams

with open('News_to_sentiment.txt', 'r', encoding='utf8') as fin:
    lines = fin.readlines()

list_of_words = []

for line in lines:
    words = line.split(" ")
    for word in words:
        word2 = word.strip(string.punctuation)
        try:
            word_with_pos = word2 + '(' + morph.parse(word2)[0].tag.POS + ')'
            list_of_words.append(word_with_pos)
        except Exception as err:
            pass

bigrams = extract_ngrams(list_of_words, 2)

d = {}
for word in bigrams:
    d[word] = d.get(word, 0) + 1

with open("News_bigrams.txt", 'w', encoding='utf8') as fout:
    for key, value in sorted(d.items(), key=lambda x: x[1], reverse=True):
        fout.write('{0} {1}\n'.format(key, value))



