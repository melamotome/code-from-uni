main :: IO()
main = print "hello"
{-Не забывайте писать в конце определений типов deriving Show, 
чтобы значения ваших типов можно было распечатывать.

Тип данных “Точка на плоскости”.
1/Создайте свой собственный тип данных Point (точка на плоскости) 
У каждой точки есть две Int координаты.
2/Реализуйте функцию vectorSum поэлементного сложения точек
3/Реализуйте функцию vectorLength, которая считает длину вектора 
из нуля до точки-}
data Point = Point Int Int deriving Show

vectorSum :: Point -> Point -> Point
vectorSum (Point a1 b1) (Point a2 b2) = Point (a1 + a2) (b1 + b2)

vectorLength :: Point -> Float
vectorLength (Point a b) = sqrt $ fromIntegral (a * a + b * b)
{-Тип данных “Фигура”
1/Создайте тип данных Figure, значениями этого типа могут быть круги, 
для которых известен радиус. Прямоугольник, у которого известно две стороны. 
Равносторонний треугольник, у которого тоже известна одна сторона.
2/Реализуйте функцию area :: Figure -> Double, 
которая вычисляет площадь фигуры-}
data Figure = Circle Int | Rect Int Int | Triangle Int deriving Show

area :: Figure -> Float
area (Rect w h) = fromIntegral (w * h)
area (Circle r) = fromIntegral (r * r) * 3.1415926535
area (Triangle a) = (sqrt 3) * fromIntegral (a * a) / 4
{-Введем тип “список”:
 data MyList = Empty | Cons Int MyList deriving Show 
 -- Empty
-- Cons 42 Empty               42 `Cons` Empty
-- Cons 42 (Cons 123 Empty)    42 `Cons` (123 `Cons` Empty)
-- []
-- 42:[]
-- 42:123:[]
Примером вызова тогда будет lenL (Cons 42 (Cons 123 Empty))
Реализуйте для такого списка операции:
1/Сумма всех элементов списка
2/Длина списка
3/Переворачивание списка-}
data MyList = Empty | Cons Int MyList deriving Show

sumL :: MyList -> Int
-- sumL l = sH 0 l where
sumL = sH 0 where 
             sH c Empty = c
             sH c (Cons s l) = sH (c+s) l

lenL :: MyList -> Int
--lenL l = sH 0 l where
lenL = sH 0 where
                 sH c Empty = c
                 sH c (Cons s l) = sH (c+1) l

revL :: MyList -> MyList
revL l = sH Empty l where
                     sH l Empty = l
                     sH l (Cons s t) = sH (Cons s l) t 