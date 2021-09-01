#!/usr/bin/python

#2 Завести модуль, содержащий базовые
#лингвистические функции (см. пример
#частотного словаря) и проверить работу
#модуля из отдельного скрипта


import hw421
import sys
if __name__ == '__main__':
    try:
        print("Введите строку и букву, чтобы узнать, сколько раз буква встречается и набор уникальных букв")
        a = sys.argv[1]
        b = sys.argv[2]
        print(a)
        print(b)
        print(str(hw421.oftendict(a)))
        print(str(hw421.howmany(a, b)))
    except:
        print('A string and a character expected')



