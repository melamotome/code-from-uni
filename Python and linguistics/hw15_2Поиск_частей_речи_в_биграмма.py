#!/usr/bin/python

'''
Написать функцию, которая принимает на вход
пару частей речи (например, (NOUN, NOUN)) и
выводит в файл только те элементы построенного в
задаче 1 частотного словаря, которые соответствуют
заданному фильтру.
'''
import string
import re

def fitsPos(pos1, pos2, filenamein, filenameout):
    with open(filenamein, 'r', encoding='utf8') as fin, open(filenameout, 'w', encoding='utf8') as fout:
        lines = fin.readlines()

        pos1 = "(" + pos1 + ")"
        pos2 = "(" + pos2 + ")"

        poses_pattern = re.compile('\(\w+\)')
        fitLines = []

        for line in lines:
            current_pos1, current_pos2 = re.findall(poses_pattern, line)

            if current_pos1 == pos1 and current_pos2 == pos2:
                fout.write(line)


fitsPos("NOUN", "NOUN", "News_bigrams.txt", "News_bigrams_filter.txt")



