-- Повторите код с лекции, который создает класс Reversible с методом rev. 
class Reversible a where
    rev :: a -> a
-- Потом реализуйте переворачивание Int, cписка.
instance Reversible Int where 
    rev = list2num.num2list where 
                        list2num = foldl (\a x -> 10 * a + x) 0
                        num2list 0 = []
                        num2list n = (n `mod` 10) : num2list (n `div` 10)

instance Reversible a => Reversible [a] where 
                                         rev = map rev . reverse

-- Реализуйте для типа Point следующие классы. 
data Point = Point Int Int

vectorSum :: Point -> Point -> Point
vectorSum (Point a1 b1) (Point a2 b2) = Point (a1 + a2) (b1 + b2)

vectorLength :: Point -> Float
vectorLength (Point a b) = sqrt $ fromIntegral (a * a + b * b)
-- Проверьте, что с типом Point начнут работать встроенные функции 
-- min, minimum, print и другие, ожидающие типы классов Show, Eq, Ord
-- (это после того, как нижние классы реализованы)
-- 1 Reversible: при переворачивании точки меняются местами координаты, 
-- и каждая из них переворачивается.
instance Reversible Point where 
    rev (Point a b) = (Point (rev b) (rev a))
-- 2 Show: точка записывается в виде строки текста как два числа 
-- через точку с запятой.
-- Show - Defines the function show, which converts a value into a string,
--  and other related functions.
instance Show Point where
    show (Point a b) = concat [show a, ";", show b]
-- 3 Eq: две точки можно сравнить на одинаковость
-- Eq - Equality operators == and /=
instance Eq Point where
    (Point a1 b1) == (Point a2 b2) = (a1 == a2) && (b1 == b2)
-- 4 Ord: две точки можно сравнить, при этом реально 
-- сравниваются расстояния до (0; 0)
-- Ord - Comparison operators < <= > >=; min, max, and compare.
instance Ord Point where
    -- compare :: a -> a -> Ordering
    -- data Ordering - Constructors LT EQ GT  
    compare p1 p2 = compare (vectorLength p1) (vectorLength p2)