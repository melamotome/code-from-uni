#!/usr/bin/python

'''1Скачать словарь положительно и отрицательно окрашенных слов (словарь тональностей)
2Лемматизировать текст с помощью Mystem или pymorphy2
3Определить тональность текста, используя словарь тональностей

Для набора предложений определить тональность каждого предложения
и записать результат в CSV-файл
(первый столбец – предложение, второй столбец – число -1, 0 или 1).
'''

#!/usr/bin/python

import string
import re
from collections import defaultdict

import pymorphy2

PNCT = string.punctuation


def lemmatize(morph, file_in, file_out):
    with open(file_in, 'r', encoding='UTF8') as fin, open(file_out, 'w', encoding='UTF8') as fout:
        for line in fin:
            line = line.strip()
            if not line:
                continue

            lemmas = []

            for word in line.split():
                word = word.strip(PNCT)
                if not word:
                    continue
                try:
                    lemmas.append(morph.parse(word)[0].normal_form)  # 0-наиболее вероятный в списке всего такого
                except Exception as e:
                    print('No lemma for:', word, ', error', e)
                    continue

            fout.write(' '.join(lemmas) + '\n')

    return True

def lemmatize_sentences(morph, file_in, file_out):
    with open(file_in, 'r', encoding='UTF8') as fin, open(file_out, 'w', encoding='UTF8') as fout:
        for line in fin:
            line = line.strip()
            if not line:
                continue

            for sentence in re.split(re.compile(' *[.?!]+ *'), line):
                lemmas = []
                for word in sentence.split():
                    word = word.strip(PNCT)
                    if not word:
                        continue
                    try:
                        lemmas.append(morph.parse(word)[0].normal_form)  # 0-наиболее вероятный в списке всего такого
                    except Exception as e:
                        print('No lemma for:', word, ', error', e)
                        continue

                fout.write(' '.join(lemmas) + '\n')

    return True

def parse_sentiment(file_in):
    sentim = defaultdict(list)
    with open(file_in, 'r', encoding='UTF8') as fin:
        for line in fin:
            line = line.strip()
            if not line:
                continue
            items = line.split('\t')
            if len(items) != 2:
                continue
            try:
                sentim[items[0]].append(int(items[1]))
            except Exception as e:
                print('Items:', items, ' error:', e)
    return sentim


def dump_sentiment(sentim, file_out):
    with open(file_out, 'w', encoding='UTF8') as fout:
        for word, scores in sorted(sentim.items()):
            fout.write('{0}\t{1}\n'.format(word, sum(scores) / len(scores)))
    return True

def load_sentiment(file_in):
    sentim = {}
    with open(file_in, 'r', encoding='UTF8') as fin:
        for line in fin:
            line = line.rstrip()
            if not line:
                continue
            items = line.split('\t')
            #sentim[items[0]] = float(items[1])
            #or
            word, score = items
            sentim[word] = float(score)
    return sentim

def analyze_and_out_CSV(file_in, file_in_lemmas, sentim, file_csv_out):
    with open(file_in_lemmas, 'r', encoding='UTF8') as fin_lemmas:
        grades = []
        for line in fin_lemmas:
            line = line.strip()
            if not line:
                continue

            scores = []
            words = line.split()
            scores.extend([sentim.get(word, 0.0) for word in words])
            score = sum(scores) / len(scores)
            if score < 0:
                grade = -1
            elif score > 0:
                grade = 1
            else:
                grade = 0

            grades.append(grade)

    with open(file_in, 'r', encoding='UTF8') as fin, open(file_csv_out, 'w', encoding='utf8') as fout:
        i = 0
        for line in fin:
            sentences = re.split(re.compile(' *[.?!]+ *'), line);
            for sentence in sentences:
                try:
                    fout.write('"{0}","{1}"\n'.format(sentence, grades[i]))
                    i += 1
                except Exception as err:
                    pass




if __name__ == '__main__':
    file_in = 'News_to_sentiment.txt'
    file_lemma = 'News_to_sentiment_lemma_pymorphy2.txt'
    file_lemma_sentences = 'News_to_sentiment_sentences_lemma_pymorphy2.txt'
    file_csv_out = 'News_to_sentiment_sentimented_sentences.csv'

    sentim_weights_file = 'sentiment_weights.txt'

    morph = pymorphy2.MorphAnalyzer()
    # lemmatize(morph, file_in, file_lemma)
    lemmatize_sentences(morph, file_in, file_lemma_sentences)

    sentim = load_sentiment(sentim_weights_file)

    analyze_and_out_CSV(file_in, file_lemma_sentences, sentim, file_csv_out)




