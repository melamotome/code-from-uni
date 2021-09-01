#!/usr/bin/python

#1 Написать скрипт, принимающий на вход
#два числа и название операции (+, -, *, /)
#в качестве аргументов командной строки,
#возвращающий результат этой операции.
#Предусмотреть проверку корректности
#вводимых данных

import sys

def oper(a, b, opr):
        if opr == "+":
            c = a + b
        elif opr == "-":
            c = a - b
        elif opr == "/":
            c = a / b
        elif opr == "*":
            c = a * b
        return c

if __name__ == '__main__':
    try:
        print("Введите два числа и операцию между ними (+, -, /, *)")
        a = int(sys.argv[1])
        b = int(sys.argv[2])
        opr = sys.argv[3]
        print(oper(a, b, opr))
    except:
        print('Two integer parameters and then operation expected')


"""import sys
if __name__ == "__main__":
    for param in sys.argv:
        print (param)"""
