#!/usr/bin/python

import sys
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt # matplotlib required

from sklearn.linear_model import LinearRegression


filename = 'psycho_metrics_with_wall_090318.xlsx'
d = pd.read_excel(filename, header=0, index_col=0, na_values='-')  # requires xlrd
#Вот добавили, какие ещё нан считаем кроме н а.
#хедер - названия столбцов взяли первую строку, строки пронумеровались сами
#print(d)
print('Header:', d.columns.values.tolist())
print('Columns:', len(list(d.columns)))
print('Rows:', len(d.index.values.tolist()))


#colname = 'Знаков в графе «Источники вдохновения»'
colname = 'Экстраверсия – Интроверсия'

print(d[colname])

# Working with NaN values: https://pandas.pydata.org/pandas-docs/stable/missing_data.html

print(d[colname].isna())

# Amount of NaN values
print('Amount of NaNs:', d[colname].isnull().sum())

print(d[colname].value_counts(dropna=False)) #это вот вывело отсортированный списочек количества значений

# Amount of NaN values in each column
#for col in d:
#    print(d[col].isnull().sum(), col)


# Fill NaN with mean values но мы не будем так делать
#d.fillna(d.mean()[colname], inplace=True)
#инплейс - на месте меняем

#print(d[colname])
#print(d[colname].isna())

# Drop NA values будем так делать
d.dropna(axis=0, subset=[colname], how='any', inplace=True)
#0-убираем строчки. список тех столбцов, по которым смотрим нан значения. how - если несколько столбцов,
#  в одном нан, в другом не нан. Это вот any all варианты разрешения этого
print('Amount of NaNs:', d[colname].isnull().sum())
print('Columns:', len(list(d.columns)))
print('Rows:', len(d.index.values.tolist()))


#d_short = d.loc[:, 'Возраст':'Фотографии за год - Пропорция цветов (переменная 5) - Пурпурный']
#срез со вклбчением границ. первое - по икс, берём все, если так
#d_short = d.loc[:, 'Возраст':]
#print((d_short.columns))
#эти две вот одно и то же, ибо вторая колонка последняя

d.drop(d.columns[d.columns.get_loc('Доминирование – Подчиненность'): d.columns.get_loc('XII')], axis=1, inplace=True)
#Это мы убирам ненужное. гетлок даёт индекс 1-вырезаем по столбцам.
print('Columns:', len(list(d.columns)))
print('Rows:', len(d.index.values.tolist()))


#Попытка увидеть кореллирование
print('Extracting two columns...')
colname2 = 'Лайков сообщений пользователя на стене'
colname2 = 'Друзей'
#colname2 = 'Свои сообщения за года на стене'
#colname2 = 'Количество фотографий с геоданными за год'
#colname2 = 'Знаков в графе «О себе»'
colname3 = 'Свои сообщения за месяц на стене'
colname4 = 'Лайков сообщений пользователя на стене'
#Чтобы всю матрицу не сортировать. копи - глубинная копия.
d_short = d.loc[:, [colname, colname2]].copy()#колнейм у нас там было уже давно
print('Two columns extracted')
print('Sorting values...')
d_short.sort_values(by=colname2, inplace=True)
print('Values sorted')
#print(d_short)

# Plot one column against another
d_short.plot(y=colname, x=colname2, xlim=(100, 500))
#кслим - границы
plt.show()


# Use selected columns to train LinearRegression
d_lr = d.loc[:, [colname, colname2, colname3, colname4]].copy()
d_lr.dropna(axis=0, inplace=True)
#убираем строчки

#предсказыаем экстраверсию
lr = LinearRegression()
lr.fit(d_lr[[colname2, colname3, colname4]], d_lr[colname])
print(lr.coef_)


