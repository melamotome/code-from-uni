#!/usr/bin/python
'''Получить любой JSON с сайта
paraphraser.ru/api/form, воспользовавшись методом
«Вектора слов», и сохранить его в файл. Загрузить
содержимое файла с помощью Python. Если файл
сформирован успешно (code = 0), то вывести все
слова из вектора слов (vector). В противном случае
вывести сообщение об ошибке (msg). ЗАМЕЧАНИЕ:
векторов может быть несколько, например, для
запроса «малина».'''

import json
with open('hw111json.json', 'r', encoding = 'UTF8') as fin:
    data = json.load(fin)


    if data["code"] == 0:
        for number, content in data["response"].items():
            print(content["vector"])
    else:
        print(data["msg"])
