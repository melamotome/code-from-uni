# Микро- и макроусреднение - Confusion Matrix n*n или 2*2, взвешенное усреднени(сумма коэффициентов единица)
# Ищем параметры, а потом считаем ошибочные меры(последний слайд 5 лекции)
# C = 10000
# Менять Window и Size в модели, проверить разные scores (они --> 1)
from sklearn.model_selection import train_test_split, GridSearchCV
from sklearn.linear_model import LogisticRegression
from sklearn.metrics import classification_report
from sklearn.metrics import accuracy_score, precision_score, recall_score, f1_score, roc_auc_score
import pandas as pd
import glob
import numpy
import re
from gensim.models import Word2Vec
from sklearn.model_selection import train_test_split
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.feature_extraction.text import TfidfVectorizer

#Заменяем классы -2 на -1 и 2 на 1 чтобы запихнуть в классификацию нашу
def reduce_classes(filename):
    with open(filename, 'r', encoding='utf8') as inf, open('logistic_'+filename, 'w', encoding='utf8') as ouf:
        for line in inf:
            text, tone = line.rsplit('\t', maxsplit=1)
            if not tone or not text:
                continue
            if '-2' in tone:
                tone = '-1\n'
            elif '2' in tone:
                tone = '1\n'
            ouf.write('{0}\t{1}'.format(text, tone))

def my_tokenizer(s):
    return s.split()

def matrix_scikit(nameOfFile, nsOfRows, vectorizerType, w2vModify, w2vmodel):
    with open(nameOfFile, 'r', encoding='utf8') as fin:
        if nsOfRows == 0:
            corpus = fin.readlines()
        else:
            corpus = fin.readlines()[:nsOfRows] #Типа ограничили колво обрабатываемых строк
    score = []
    corpusClean = []
    for line in corpus:
        line, s = line.rsplit('\t', maxsplit=1)
        try:
            score.append(int(s))
            corpusClean.append(line)
        except:
            continue
    if vectorizerType == "tf-idf":
        vectorizer = TfidfVectorizer(tokenizer=my_tokenizer)
    elif vectorizerType == "regular":
        vectorizer = CountVectorizer()

    X = vectorizer.fit_transform(corpusClean)
    y = numpy.array(score)

    if w2vModify:
        model = Word2Vec.load(w2vmodel)

        #Строим вторую матрицу
        vectors = []
        for word in vectorizer.get_feature_names():
            if word in model.wv:
                vectors.append(model.wv[word])
            else:
                vectors.append(numpy.zeros(model.vector_size))

        X = X * numpy.array(vectors)


    return X, y

def make_log(log_name, models, Cs, accuracys, precisions, recalls, f1s):
    with open(log_name, 'w', encoding='utf8') as fout:
        for model, C, accuracy, precision, recall, f1 in zip(models, Cs, accuracys, precisions, recalls, f1s):
            fout.write('{0}\t{1}\t{2}\t{3}\t{4}\t{5}\n'.format(model, C, accuracy, precision, recall, f1))

#Функция получает файл csv с номерами строк-1 и их настоящими и предсказанными тональнастями.
# Выводит те строки,  которыъ они отличаются
def printOutMistakenSentences(filename, textname, csvname):
    with open(csvname, 'r', encoding='utf8') as inf:
        inf.readline()
        idx = []
        for line in inf:
            list = line.split(',')
            if list[1] != list[2]:
                idx.append(int(list[0]))

    with open(textname, 'r', encoding='utf8') as inf, open(filename, 'w', encoding='utf8') as ouf:
        i = 0
        for line in inf:
            if i in idx:
                ouf.write('{0}'.format(line))
            i = i + 1

#this function gets a w2v modified X matrix and adds to it
#a feature of number of part of speech that the sentence has
#fileIn should contain mystem version of sentences in X
def addPoS(X, fileIn, PoS):
    with open(fileIn, 'r', encoding='utf8') as fin:
        nums = []
        parsePat = re.compile('\{.+?\}')
        splitter = re.compile('[=?,]+')
        for line in fin:
            #сейчас будет тупо копия обработика майстема с той штуки,
            # потому что в иксе выброшены по какому-то принципу были строки
            line, tone = line.rsplit('\t', maxsplit=1)
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
                        if expressions[1] == 'CONJ' or expressions[1] == 'PR':
                            continue
                        else:
                            wordsNew.append(expressions[0])
                if not wordsNew:
                    continue
                else:
                #конец копии
                    nums.append(line.count(PoS))

    print(len(nums))
    print(X.shape)
    # nums = pd.Series(nums, index=X.index)
    # return X.concat([X, nums], axis=1)

#this function gets a w2v modified X matrix and adds to it
#four features one is the number of :-), then :-), then ))))))))), then (((((((((((.
#file in is the source file for X but with commas and stuff. Oh, and now it adds ! and ? stuff
def addSmiles(X, fileIn):
    with open(fileIn, 'r', encoding='utf8') as fin:
        nums1 = []
        nums2 = []
        nums3 = []
        nums4 = []
        nums5 = []
        pat1 = re.compile('(([:=])-?\))|(\(-?([:=]))')
        pat2 = re.compile('(([:=])-?\()|(\)-?([:=]))')
        pat3 = re.compile('!+\?+')
        for line in fin:
            nums1.append(len(re.findall(pat1, line)))
            nums2.append(len(re.findall(pat2, line)))
            nums3.append(line.count('(') - line.count(')')) #это признак всяких )))))))))) и (((((((
            nums4.append(line.count('!'))
            nums5.append(len(re.findall(pat3, line)))
    print(len(nums1))
    print(X.shape)
    # nums = pd.Series(nums, index=X.index)
    # return X.concat([X, nums], axis=1)


if __name__ == '__main__':
    '''
    #reduce_classes('linis_data_clean_Regression.txt')
    #Часть для регрессии, отделённая от той, на которой тренировался в2в.
    #Эта, потому что с в2в и без майстема у меня получилась меньшая ошибка
    models = []
    Cs = []
    accuracys = []
    precisions = []
    recalls = []
    f1s = []

    for model in glob.glob('w2vlogMod*.model'):
        X, y = matrix_scikit("logistic_linis_data_Regression_mystemParsedMod.txt", 0, "tf-idf", True, model)

        #добавляем нумерацию стобцов и строк пандой
        X = pd.DataFrame(X)
        y = pd.Series(y)
        #разделяем, 50 на тренировку, по 25 на тест и вал
        X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.5, random_state=0)
        X_test, X_val, y_test, y_val = train_test_split(X_test, y_test, test_size=0.5, random_state=0)


        for C in [0.1, 1, 10, 100, 1000]:
            clf = LogisticRegression(C=C)
            clf.fit(X_train, y_train)
            y_pred = clf.predict(X_val)

            accuracy = accuracy_score(y_val, y_pred)
            precision = precision_score(y_val, y_pred, average='macro')
            recall = recall_score(y_val, y_pred, average='macro')
            f1 = f1_score(y_val, y_pred, average='macro')
            #average - потому что классификация у нас небинарная. Макро - отдельно считаем по парам и берём среднее

            print('C {0}, Model {1},accuracy {2}, precision {3}, recall {4}, fscore {5}\n '.format(C, model, accuracy, precision, recall, f1))

        del model
        del X
        del y
    '''

    #make_log("LogisticRegressionparams_log500.csv", models, Cs, accuracys, precisions, recalls, f1s)
    #Это мы всё делаем, чтобы отобрать лучшие параметры модели, чтобы их выбрать для работы с тестовыми данными

    '''
    #Вот работа с тестовыми данными. Представим, что это лучшие параметры.
    X, y = matrix_scikit('logistic_linis_data_clean_Regression.txt', 0, "tf-idf", True, "w2vlog_300_9_19.model")

    # добавляем нумерацию стобцов и строк пандой
    X = pd.DataFrame(X)
    y = pd.Series(y)
    # разделяем, 50 на тренировку, по 25 на тест и вал
    X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.5, random_state=0)
    X_test, X_val, y_test, y_val = train_test_split(X_test, y_test, test_size=0.5, random_state=0)

    clf = LogisticRegression(C=1000)
    clf.fit(X_train, y_train)
    y_pred = clf.predict(X_val)

    #сравниваем резы Xtest и ytest
    y_predTest = clf.predict(X_test)
    y_predTest = pd.Series(y_predTest, index=y_test.index) #это я перевожу чтобы склеить вместе
    #Но проблема, индексы у его с 1, попытаемся скопировать индексы перемешанные теста на предтест, порядок-то тот же самый.
    Мы это, кажется, не закончили???

    # print(y_predTest)
    # print(y_test)

    #выведем их в файлик
    ys = pd.concat([y_test, y_predTest], axis=1)
    print(ys)
    ys.to_csv("LogistRegrYCompare.csv")

    #printOutMistakenSentences("LogistRegrMistakenSent.txt", 'logistic_linis_data_clean_Regression.txt', "LogistRegrYCompare.csv")
    #printOutMistakenSentences("LogistRegrMistakenSentWithStuff.txt", "linis_2.txt", "LogistRegrYCompare.csv")

    '''






    #Теперь я буду пытаться просто хорошо улучшить модель. Основной код:

    reduce_classes("linis_data_Regression_mystemParsedMod.txt")
    X, y = matrix_scikit("logistic_linis_data_Regression_mystemParsedMod.txt", 0, "tf-idf", True, 'w2vMystemParsedMod500_8_11.model')

    #добавляем нумерацию стобцов и строк пандой
    X = pd.DataFrame(X)
    y = pd.Series(y)

    print(X)

    #print(addPoS(X, "linis_data_Regression_mystem.txt", "ADV"))
    #print(addSmiles(X, "linis_data_Regression_mystem.txt"))


    #разделяем, 50 на тренировку, по 25 на тест и вал
    X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.5, random_state=0)
    X_test, X_val, y_test, y_val = train_test_split(X_test, y_test, test_size=0.5, random_state=0)

    clf = LogisticRegression(C=1000)
    clf.fit(X_train, y_train)
    y_pred = clf.predict(X_val)

    f1 = f1_score(y_val, y_pred, average='macro')
    print(f1)

     #average - потому что классификация у нас небинарная. Макро - отдельно считаем по парам и берём среднее




