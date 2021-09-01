#!/usr/bin/python

from gensim.models import Word2Vec
from itertools import product
class IterSent(object):
    def __init__(self, filename):
        self.filename = filename

    def __iter__(self):
        with open(self.filename, 'r', encoding='UTF8') as fin:
            for line in fin:
                yield line.split()

#Тренировка модели
sentences = IterSent("linis_data_w2v_mystemParsedMod.txt")
model = Word2Vec(sentences, size=300, window=9, min_count=5)

#Делаем для логистической регрессии кучку моделей тут
#запустить позже и пройти все
#sizes = range(100, 600, 100) #100-500 с шагом 100
#windows = range(1, 11)
#mid_counts = range(1, 20, 3)

#временное на паре чтоб побыстрее. тут 27 вариантов в итоге будет только, не 300
'''
sizes = [500]
windows = range(2, 10, 3)
mid_counts = range(1, 15, 5)

sentences = IterSent('linis_data_clean_w2v.txt')
for size, window, mid_count in product(sizes, windows, mid_counts):
    model = Word2Vec(sentences, size=size, window=window, min_count=mid_count)
    model.save('w2vlogMod_{0}_{1}_{2}.model'.format(size, window, mid_count))
    print('model with {0}_{1}_{2} is done'.format(size, window, mid_count))
    del model
'''

#Загрузка и всякое
fname = 'w2vMystemParsedMod300_9_5.model'
model.save(fname)
# model = Word2Vec.load(fname)

#Некоторые действия
# word = 'кто'
# if word in model.wv:
#     print(model.wv[word])
# else:
#     print("Такого слова нет")

# print(model.wv.most_similar(positive=['в', 'на'], negative=['не']))
# print(model.wv.similarity('в', 'на'))
#
# print(model.wv.most_similar(positive=['что', 'два']))
# print(model.wv.similarity('просит', 'милостыню'))
