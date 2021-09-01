import numpy
m1 = numpy.matrix('1 2; 3 4; 5 6')
m33 = numpy.matrix('1 2; 3 4')
m2 = numpy.matrix([[1, 2], [3, 4], [5, 6]])
m2 = numpy.matrix([(1, 2), [3, 4], [5, 6]])
v1 = numpy.array([1, 2, 3, 4, 5])
v2 = numpy.array([1, 2])
print(m33)
print(m33 * m33)
print(m1.shape) #размерность, строки - столбцы
m = numpy.matrix([[1], [2], [3]])
print(m.shape)
n = numpy.matrix([[1, 2, 3]])
print(n.shape)
#эррей - вектор, матрикс - матрица. Точка после числа - действительное число
z2 = numpy.zeros((2,3)) #подаём кортеж размерности шейп вот
