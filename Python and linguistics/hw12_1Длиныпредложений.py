#!/usr/bin/python

'''#1Написать функцию, вычисляющую длины предложений в
тексте (в словах), а также среднюю длину всех
предложений.'''
import string
import re

def textLength(text):
    #Убираю точку или подобное в конце текста, чтобы избежать пустое множество
    if text[len(text) - 1] in string.punctuation:
        text = text[:len(text) - 1]

    #Делю на предложения
    sentences = re.split(r' *[.?!]+ *', text)

    #Получаю слова для их подсчёта
    dividedSentences = []
    for sentence in sentences:
        dividedSentences.append(sentence.split(" "))

    print(dividedSentences)
    #Вычисление длин
    k = 0
    for i in range(0, len(dividedSentences)):
        length = len(dividedSentences[i])
        if length > 0:
            print('The length of the {0} sentence is {1} words'.format(i + 1, length))
            k += length
    print('The average length of the text is {0:1.1f} words'.format(k / len(dividedSentences)))


text = "The first sentence. The second. And this is the third sentence."
textLength(text)





