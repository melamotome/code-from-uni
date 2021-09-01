main :: IO()
main = print "hewwo"

f :: Int -> Int
f x = x + 1

g :: Int -> Int -> Int
g 0 x = x
g x y = x
-- Модуль, 1 конструкция
abs1 :: Int -> Int
abs1 x = if x < 0 then (-x) else x
-- Модуль, 2 конструкция
abs2 :: Int -> Int
abs2 x | x < 0 = (-x)
       |otherwise = x
-- Определитель знаков, вторая(третья?) конструкция
sign1:: Int -> Int
sign1 x | x < 0 = -1
        | x == 0 = 0
        | otherwise = 1
-- Факториалы с хвостовой рекурсией
factHelper :: Integer -> Integer -> Integer
factHelper 0 f = f
factHelper n f = factHelper (n-1) (f*n)

fact :: Integer -> Integer
fact n = factHelper n 1
-- Фибоначчи
-- 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765, 10946
fib :: Integer -> Integer
fib 0 = 1
fib 1 = 1
fib n = fib (n-1) + fib (n-2)
-- Улучшенный Фибоначчи
fib2 :: Integer -> Integer
fib2 n = fibHelper n 0 1
fibHelper :: Integer -> Integer -> Integer -> Integer
-- fibHelper n a b | n == 0 = a
--                 | otherwise = fibHelper (n-1) b (a+b)
fibHelper 0 a _ = a
fibHelper n a b = fibHelper (n-1) b (a+b)

fib2' :: Integer -> Integer
fib2' n = helper n 0 1 where 
                        helper 0 a _ = a
                        helper n a b = helper (n-1) b (a+b)

fib2'' n = let
            helper 0 a _ = a
            helper n a b = helper (n-1) b (a+b) 
           in
            helper n 0 1

-- СПИСКИ
-- Примеры реализаций уже существующих функций с пары
sum1 :: [Int] -> Int
sum1 [] = 0
sum1 (x:l) = x + sum1 l

-- Попробуем хвостовую рекурсию
sum2 :: [Int] -> Int
sum2 l = sum2helper l 0

sum2helper :: [Int] -> Int -> Int
sum2helper [] a = a
sum2helper (x:l) a = sum2helper l (x+a) 
-- пример оригинала [10,20,30] !! 2 получится 30
drop1 :: Int -> [String] -> [String]
drop1 0 l = l
drop1 n (x:l) =  drop1 (n-1) l 

--Теперь дз-тренировка
-- Аналог ++
concat1 :: [String] -> [String] -> [String]
concat1 a b = concatHelper (concatHelper b (concatHelper a [])) []

concatHelper :: [String] -> [String] -> [String]
concatHelper [] c = c
concatHelper (x:l) c = concatHelper l (x:c)

--Аналог product
prod1 :: [Int] -> Int
prod1 [] = 1
prod1 (x:l) = x * prod1 l

--Аналог maximum
max1 :: [Int] -> Int
max1 a = maxHelper a 0

maxHelper :: [Int] -> Int -> Int
maxHelper [] c = c
maxHelper (x:l) 0 = maxHelper l x
maxHelper (x:l) c | x > c = maxHelper l x
                  | otherwise = maxHelper l c

--Аналог minimum
min1 :: [Int] -> Int
min1 a = minHelper a 0

minHelper :: [Int] -> Int -> Int
minHelper [] c = c
minHelper (x:l) 0 = minHelper l x
minHelper (x:l) c | x < c = minHelper l x
                  | otherwise = minHelper l c

-- Аналог take
take1 :: Int -> [String] -> [String]
take1 n a = reverse1 (takeHelper n a []) []

takeHelper :: Int -> [String] -> [String] -> [String]
takeHelper 0 l c = c
takeHelper n (x:l) c =  takeHelper (n-1) l (x:c)
-- то же самое что concatHelper кстати:
reverse1 :: [String] -> [String] -> [String]
reverse1 [] c = c
reverse1 (x:l) c = reverse1 l (x:c)

-- Задания с сайта
--length1 длина списка
length1 :: [a] -> Int
length1 a = lengthHelper a 0

lengthHelper :: [a] -> Int -> Int
lengthHelper [] l = l
lengthHelper (s:a) l = lengthHelper a (l + 1)
--last1 возвращает последний элемент списка
last1 :: [a] -> a
last1 list = lastHelper list (head list)

lastHelper :: [a] -> a -> a
lastHelper [] el = el
lastHelper (s:l) el = lastHelper l s
--push1 дописать элемент в конец списка
push1 :: [a] -> a -> [a]
push1 ls a = reverse2(a:(reverse2 ls [])) []

reverse2 :: [a] -> [a] -> [a]
reverse2 [] c = c
reverse2 (x:l) c = reverse2 l (x:c)
--repeat1 :: Int -> Char -> String повторить элемент указанное число раз
repeat1 :: Int -> Char -> String
repeat1 n c = repeatHelper n c []

repeatHelper :: Int -> Char -> [Char] -> String
repeatHelper 0 c l = l
repeatHelper n c l = repeatHelper (n-1) c (c:l)
--get1 получить элемент списка по его индексу
get1 :: Int -> [a] -> a
get1 n l = helper n 0 l where
	                     helper m (h:l) | n - 1 == m = h
	                     helper i (h:l) = helper (i + 1) l

-- getHelper :: Int -> Int -> [a] -> a
-- getHelper n m (h:l) | n - 1 == m = h
-- getHelper n i (h:l) = getHelper n (i + 1) l
--rev1 перевернуть список
rev1 :: [a] -> [a]
rev1 a = reverse2 a []