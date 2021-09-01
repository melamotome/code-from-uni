import string
import numpy
from gensim.models import Word2Vec
from sklearn.model_selection import train_test_split
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.linear_model import LinearRegression
from sklearn.metrics import mean_squared_error
from sklearn.feature_extraction.text import TfidfVectorizer

#Получаю матрицу с признаками - словами, строками - строками, которые типа "файлы", значения - частота
#Сами достали матрицу(не используем для регрессии)
def matrix(nameOfFile): #надо возвращать матрицу
    with open(nameOfFile, 'r', encoding='utf8') as fin:
        lines = fin.readlines()

    words = []
    textLines = []
    nLinesPassed = 0
    for line in lines:
        #в этом кусочке я ограничиваю колво строк, которые я вот обрабатываю
        if nLinesPassed > 9:
            break
        nLinesPassed += 1

        line = line.strip(string.punctuation + string.whitespace)
        line = line.split('\t')[0]
        textLine = []
        for word in line.split(' '):
            for chara in word:
                if chara in string.punctuation:
                    word = word.replace(chara,"")
            if not word:
                continue
            textLine.append(word)
            words.append(word) #words это список всех-всех вообще слов
        textLines.append(textLine) #Это вот список строк без пунктуации
        words = sorted(set(words))

    numbersTotal = []
    for line in textLines:
        numbers = [0] * len(words) #делаю пустой список, где сопоставлено число каждому существующему слову
        for i in range(0, len(line)):
            for j in range(0, len(words)):
                if line[i] == words[j]:
                    numbers[j] += 1
        numbersTotal.append(numbers) #список списков(размером с количество слов вообще, которые отсортированиы там были) чисел, где каждой строке соответствует список, где числа стоят в соответствии с тем, как слова появляются в той штуке
    return numpy.matrix(numbersTotal)

#Пытаемся сделать, чтобы ошибка была не странная, чтобы разделяло не по _
def my_tokenizer(s):
    return s.split()

#сцайкит нам достаёт матрицу плюс мы её модим если хотим
def matrix_scikit(nameOfFile, nsOfRows, vectorizerType, w2vModify):
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

    # Тут мы модифицируем матрицу с помощью векторов в2в. Для каждого признака есть вектор.
    # Хотим их сложить, используя взвешенную сумму вес 1 слова на вектор, вес 2 слова на вектор (верт) и тд
    # Веса берём из матрицы. Веса из строчки берём взвешенную сумму. Слово 1 столбец, его вектор в2в,
    # вектор умножаем на число той строки, складываем с перемножением другого слово, в итоге вектор размерности
    # размера вектора слова.

    #Но мы векторизируем. Нашу матрицу умножим на матрицу, где строки - слова, а столбцы - пространные понятия в2в
    #Будет по сути та же взвешенная сумма.
    if w2vModify:
        model = Word2Vec.load('w2v.model')

        #Строим вторую матрицу
        vectors = []
        for word in vectorizer.get_feature_names():
            if word in model.wv:
                vectors.append(model.wv[word])
            else:
                vectors.append(numpy.zeros(model.vector_size))

        X = X * numpy.array(vectors)


    return X, y

#Используем сцайкит для регрессии
def LinearRegression_Predict(X, y):
    regr = LinearRegression()
    #Разбиваем на тестовые и тренинговые
    X_train, X_test, y_train, y_test = train_test_split(X, y, test_size = 0.4, random_state = 0)
    regr.fit(X_train, y_train)  # Train linear regression.
    y_pred = regr.predict(X_test)  # Make predictions.
    return mean_squared_error(y_test, y_pred)

#Проверка на underfitting и overfitting
def LinearRegression_PredictTrainToo(X, y):
    regr = LinearRegression()
    #Разбиваем на тестовые и тренинговые
    X_train, X_test, y_train, y_test = train_test_split(X, y, test_size = 0.4, random_state = 0)
    regr.fit(X_train, y_train)  # Train linear regression.

    y_pred_test = regr.predict(X_test)  # Make predictions
    print("Ошибка на тестовых данных: ", mean_squared_error(y_test, y_pred_test))
    y_pred_train = regr.predict(X_train)  # Make predictions.
    print("Ошибка на тренировочных данных: ", mean_squared_error(y_train, y_pred_train))

def makeLog(nameOfFile, nameOfLog, nsOfRows):
    sizes = []
    vectorizerTypes = []
    errors = []
    uniqueWords = []
    for n in nsOfRows:
        x, y = matrix_scikit(nameOfFile, n, "regular", True)  # tf-idf or regular
        sizes.append(n)
        vectorizerTypes.append("regular")
        errors.append(LinearRegression_Predict(x, y))
        uniqueWords.append(x.shape[1])

        x, y = matrix_scikit(nameOfFile, n, "tf-idf", True)  # tf-idf or regular
        sizes.append(n)
        vectorizerTypes.append("tf-idf")
        errors.append(LinearRegression_Predict(x, y))
        uniqueWords.append(x.shape[1])

    with open(nameOfLog, 'w') as fout:
        for i in range(0, len(sizes)):
            fout.write('{0}\t{1}\t{2}\t{3}\n'.format(sizes[i], vectorizerTypes[i], errors[i], uniqueWords[i]))

#Сейчас мы попробуем сравнить, то есть найдём максимальный квадрат ошибки в каждом столбце y test и predicted
#Заодно посмотрим именно в какой строке, чтобы посмотреть, в каком типе строк майстем и немайстем косячит
#Столбцы истинное, с майстемом и без майстема(все с вордтувеком. По отдельности сделаем
def compareYs(X, y, fileOut):
    regr = LinearRegression()
    # Разбиваем на тестовые и тренинговые
    X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.4, random_state=0)
    regr.fit(X_train, y_train)  # Train linear regression.
    y_pred_test = regr.predict(X_test)  # Make predictins

    # #Вывод истинных y
    # with open(fileOut, 'w') as fout:
    #     for item in y_test:
    #         fout.write('{0:.5f}\n'.format(item))

    #Вывод y mystemа или нет
    with open(fileOut, 'w') as fout:
        for item in y_pred_test:
            fout.write('{0:.5f}\n'.format(item))

# x, y = modify_matrix(matrix_scikit('linis_data.txt', 50, regular, True))#tf-idf or regular
# print(LinearRegression_Predict(x, y))

# nsOfRows = [50, 100, 150, 200, 250, 300, 350, 400, 450, 500, 550, 600, 650, 700, 750, 800, 850, 900, 950, 1000]
# nsOfRows = [50, 100, 150, 200]
# nsOfRows = [50]
# makeLog('linis_data_clean_Regression.txt', 'linis_data_log_withw2v.csv', nsOfRows)

#Это для сравнения ошибки с модификацией матрицы и без. Вот это с
#* стоит потому что matrix scikit возвращает кортеж, это распаковка
#print("Ошибка с w2v ", LinearRegression_PredictTrainToo(*matrix_scikit('linis_data_clean_Regression.txt', 0, "tf-idf", True)))
#print("Ошибка без w2v ", LinearRegression_PredictTrainToo(*matrix_scikit('linis_data_clean_Regression.txt', 0, "tf-idf", False)))
#Вот мы это запустили и получилось, что без вордтувека был оверфиттинг

#Теперь такое же сравнение, но на этот раз со словами, проведёнными через mystem
# print("Сейчас сделано без частей речи в майстеме")
# print("Ошибка с mystem и w2v ")
# LinearRegression_PredictTrainToo(*matrix_scikit('linis_data_Regression_mystemParsed.txt', 0, "tf-idf", True))

# print("Ошибка с mystem и без w2v ")
# LinearRegression_PredictTrainToo(*matrix_scikit('linis_data_Regression_mystemParsed.txt', 0, "tf-idf", False))

#compareY
# compareYs(*matrix_scikit('linis_data_Regression_mystemParsed.txt', 0, "tf-idf", True), "TrueTestY.txt")
#compareYs(*matrix_scikit('linis_data_Regression_mystemParsed.txt', 0, "tf-idf", True), "MystemY.txt")
compareYs(*matrix_scikit('linis_data_clean_Regression.txt', 0, "tf-idf", True), "NoMystemY.txt")