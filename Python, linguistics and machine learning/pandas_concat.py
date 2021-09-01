#!/usr/bin/python

import pandas as pd
import re
'''

y_test = pd.Series([0, 1, 2, 3], index=[10, 11, 12, 13])
y_pred = pd.Series([1, 1, 1, 1], index=y_test.index)

print(y_test)
print(y_pred)

df = pd.concat([y_test, y_pred], axis=1)
print(df)

df[2] = pd.Series([9, 9, 9, 9]).values
print(df)

'''
#stuckPat = re.compile(' [a-zа-я]+[A-ZА-Я]{1}[a-zа-я]* ')
stuckPat = re.compile('(?P<first> [a-zа-яё]+)(?P<second>[A-ZА-ЯЁ]{1}[a-zа-яА-ЯA-ZёЁ]* )')
line = "блабла блабла БЛААА бЛАААА белоВеррррр    "
matches = list(re.finditer(stuckPat, line))

for match in re.finditer(stuckPat,line):
    print(match.group('first'))


#line = line.replace(line, match.group('first') + ' ' + match.group('second'))
#line = line.replace(match.group('first') + match.group('second'), match.group('first') + ' ' + match.group('second'))???
#print(re.findall(stuckPat, line))