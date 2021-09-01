main :: IO()
main = print "hewwo"
--implement filter for training
filter1 :: (a -> Bool) -> [a] -> [a]
filter1 f l = reverse (filterHelper f l [])

filterHelper :: (a -> Bool) -> [a] -> [a] -> [a]
filterHelper f [] listFinal = listFinal
filterHelper f (h:list) listFinal = filterHelper f list (filterHelper2 f h listFinal)

filterHelper2 :: (a -> Bool) -> a -> [a] -> [a]
filterHelper2 f h listFinal | f h = h:listFinal
                            | otherwise = listFinal

filter2 :: (a -> Bool) -> [a] -> [a]
filter2 _ [] = []
-- filter2 f (x:l) = if f x then x : filter2 f l else filter2 f l
filter2 f (x:l) = if f x then x:tail else tail where tail = filter2 f l

bool1 :: (Int -> Bool)
bool1 a | a > 10 = True
        | otherwise = False
bool2 a = a > 10
-- \a -> a > 10

--Пользуйтесь функциями обработки списков: map, filter и т.п.
--1Дано число, вывести список всех его положительных делителей.
task1 :: Int -> [Int]
task1 a = filter (\x -> mod a x == 0) [1..a]
--2Дано число, проверить его на простоту.
task2 :: Int -> Bool
task2 a = length (task1 a) == 2
--3Воспользуйтесь предыдущей задачей, но не перебирайте список делителей полностью, 
--проверьте только, какой делитель идёт после единицы.
task3 :: Int -> Bool
task3 a = task1 a !! 1 == a
--4Дано число, проверить, совершенное ли это число. 
--Другими словами, верно ли что число равно сумме своих делителей, не считая себя. 
--Например, 6 = 1 + 2 + 3.
task4 :: Int -> Bool
task4 a = sum (task1 a) - a == a 
--5Найдите все совершенные числа от 1 до миллиона
task5 :: Int-> [Int]
task5 a = filter task4 [1..a]
--6Решето эратосфена. Дано n, найдите все простые числа от 1 до n. 
--Прочитайте алгоритм решета Эратосфена, и реализуйте его следующим образом: 
--создайте список чисел от 2 до n, потом отделяйте от него первый элемент, 
--фильтруйте остальные, которые на него делятся. 
--Продолжайте это, пока список не кончится. 
--Обсуждение решета Эратосфена и его реализации на Haskell см. учебник Кубенского.
task6 :: Int -> [Int]
task6 n = task6Helper [2..n]

task6Helper :: [Int] -> [Int]
task6Helper [] = []
task6Helper (x:c) = x : task6Helper (filter (\b -> b `mod` x /= 0) c)
--7Простые близнецы. Дано n, найдите все пары простых чисел от 1 до n, 
--отличающихся на 2.
task7 :: Int -> [[Int]]
task7 n = task7Helper (filter task2 [1..n]) 1 []

task7Helper :: [Int] -> Int -> [[Int]] -> [[Int]]
task7Helper [] pr lf = reverse lf
task7Helper (x:l) pr lf = task7Helper l x (task7Helper2 x pr lf) 

task7Helper2 :: Int -> Int -> [[Int]] -> [[Int]]
task7Helper2 x pr lf | abs (x - pr) == 2 = [pr, x]:lf
                     | otherwise = lf