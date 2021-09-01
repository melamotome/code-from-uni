#!/usr/bin/python
# Создать систему объектов «точка» (поля -
# координаты точки) и «окружность» (поля –
# координаты центра и радиус), переопределив
# для них операцию сравнения (__eq__).
# Привести пример сравнения двух
# точек/окружностей в тексте скрипта.


class Dot(object):
    def __init__(self, x, y):
        self.x = x
        self.y = y

    def __eq__(self, other):
        return self.x == other.x and self.y == other.y


class Circle(Dot):
    def __init__(self, x, y, radius):
        super().__init__(x, y)
        self.radius = radius

    def __eq__(self, other):
        return super().__eq__(other) and self.radius == other.radius
0

dotdot = Circle(1, 2, 45)
roundy = Circle(1, 2, 45)

print(dotdot == roundy)
