import string
import re

from numpy.core.defchararray import rsplit

#Делим файл автоматически на два, на одном будем тренировать вордтувек, на другом будем запускать линейную регрессию.
#Делаем мы это для того, чтобы уменьшить ошибку лиейной регрессии, так как затем мы применим к массиву махинации,
#из-за которых он уменьшится и признаками станут не слова, а концепты, взятые из век
def divideFile(name, name1, name2):
    with open(name, 'r', encoding='utf8') as fin:
        lines = fin.readlines()
    middle = round(len(lines) / 2)
    with open(name1, 'w', encoding='utf8') as fout:
        for item in lines[:middle]:
            fout.write('{0}'.format(item))
    with open(name2, 'w', encoding='utf8') as fout:
        for item in lines[middle:]:
            fout.write('{0}'.format(item))

#Разделяем слова, очищаем от всех знаков и ссылок, переводим в ловеркейс
#Разлепляем слипнувшиеся слова
#Для регресси оставляем тональность
#Для вордтувека каждое предложение в строчке - в свою строчку, минус тональность
def cleanFile(nameIn, nameOut, sentSplitNeeded):
    # SplitNeeded means it's for word2vec
    with open(nameIn, 'r', encoding="utf8") as fin, open(nameOut, 'w', encoding="utf8") as fout, open('copy_' + nameOut, 'w', encoding="utf8") as foutCopy:
        stripper = string.whitespace + string.punctuation + "„“"
        sentPat = re.compile('[.?!]+')
        wordPat = re.compile('[\s.,()?!=/]+')
        linkPat = re.compile('https?://[a-zA-Z\d._\/-]+')
        stuckPat = re.compile('(?P<first> [a-zа-яёй]+)(?P<second>[A-ZА-ЯЁЙ]{1}[a-zа-яйА-ЯA-ZёЁЙ]* )')

        for line in fin:
            lineCopy = line
            line, tone = line.rsplit('\t', maxsplit = 1)
            if sentSplitNeeded:
                sentences = re.split(sentPat, line)
            else:
                sentences = []
                sentences.append(line)
            sentences = [sent.strip() for sent in sentences if sent.strip()]

            for sentence in sentences:
                #Разлепление слов друг от друга
                for match in re.finditer(stuckPat, sentence):
                    sentence = sentence.replace(match.group('first') + match.group('second'), match.group('first') + ' ' + match.group('second'))

                sentence = sentence.lower()
                sentence = re.sub(linkPat, ' ', sentence)
                words = re.split(wordPat, sentence)
                wordsNew = []
                for word in words:
                    word = word.strip(stripper)
                    if not word:
                        continue
                    if len(word) < 3:
                        continue
                    wordsNew.append(word)
                if not wordsNew:
                    continue
                else:
                    if sentSplitNeeded:
                        fout.write('{0}\n'.format(" ".join(wordsNew)))
                    else:
                        fout.write('{0}\t{1}'.format(" ".join(wordsNew), tone))
                    foutCopy.write(lineCopy)

def ParseMystem(nameIn, nameOut, splitNeeded):
    #сначала в консоле какое-нибедь mystem -cisd linis_data_clean_w2v.txt linis_data_w2v_mystem.txt
    #и mystem -cisd linis_data_clean_Regression.txt linis_data_Regression_mystem.txt
    #ты{ты=SPRO,ед,2-л=им}
    # - так мы доходим до первой закрывающейся скобки. Без ? жадный поиск
    with open(nameIn, 'r', encoding="utf8") as fin, open(nameOut, 'w', encoding="utf8") as fout:
    # SplitNeeded means it's for word2vec
        parsePat = re.compile('\{.+?\}')
        splitter = re.compile('[=?,]+')
        for line in fin:
            if not splitNeeded:
                line, tone = line.rsplit('\t', maxsplit=1)

            if splitNeeded:
                sentences = line.split('{\s}')
            else:
                sentences = []
                sentences.append(line)
            sentences = [sent.strip() for sent in sentences if sent.strip()]

            for sentence in sentences:
                wordsNew = []
                for bracket in re.findall(parsePat, sentence):
                    bracket = bracket.strip("{")
                    bracket = bracket.strip("}")
                    expressions = re.split(splitter, bracket)
                    if len(expressions) < 2:
                        continue
                    else:
                        # if not expressions[1]:
                        #     wordsNew.append(expressions[0] + '_UNKN')
                        # else:
                        #     wordsNew.append(expressions[0] + '_' + expressions[1])
                        #если посмотреть без частей речи:

                        #Удаление CONJ и PR
                        if expressions[1] == 'CONJ' or expressions[1] == 'PR':
                            continue
                        else:
                            wordsNew.append(expressions[0])
                if not wordsNew:
                    continue
                else:
                    if splitNeeded:
                        fout.write('{0}\n'.format(" ".join(wordsNew)))
                    else:
                        fout.write('{0}\t{1}'.format(" ".join(wordsNew), tone))

def glueNet(nameIn, nameOut):
    #Приклеивает частицы не к их словами
    with open(nameIn, 'r', encoding="utf8") as fin, open(nameOut, 'w', encoding="utf8") as fout:
        pat = re.compile(' не ')
        for line in fin:
            line = re.sub(pat, ' не', line)
            fout.write(line)

# divideFile("linis_data.txt", "linis_1.txt", "linis_2.txt")
# cleanFile("linis_1.txt", "linis_data_clean_w2v.txt", True)
# cleanFile("linis_2.txt", "linis_data_clean_Regression.txt", False)

#ParseMystem("linis_data_w2v_mystem.txt", "linis_data_w2v_mystemParsed.txt", True)
#ParseMystem("linis_data_Regression_mystem.txt", "linis_data_Regression_mystemParsed.txt", False)

#glueNet("linis_data_w2v_mystemParsed.txt", "linis_data_w2v_mystemParsedMod.txt")
#glueNet("linis_data_Regression_mystemParsed.txt", "linis_data_Regression_mystemParsedMod.txt")

