-- В этом разделе задача 1 обязательна.
-- Создайте алгебраический тип дерево 
-- Пример записи: TreeNode 4 (TreeNode 6 (TreeNode 8 EmptyTree EmptyTree) (TreeNode 8 EmptyTree EmptyTree)) (TreeNode 9 EmptyTree EmptyTree)
data Tree a = EmptyTree | TreeNode a (Tree a) (Tree a) 
-- и листовое дерево: 
-- пример записи: InnerNode (InnerNode (Leaf 1) (Leaf 2)) (Leaf 3)
data LeafTree a = Leaf a | InnerNode (LeafTree a) (LeafTree a)
-- Чем они отличаются? Как бы вы задали дерево с произвольным 
-- количеством детей в каждом узле?

-- 1 Реализуйте для дерева класс Show.
--    1 В простой версии вам нужно вывести все дерево в одну строку, 
-- расставив скобки: Например, 1[2[5, 6], 3[_,5]], 
-- здесь в корне записана 1, у нее потомки 2 и 3. 
-- У двойки потомки 5 и 6, у тройки правый потомок 5.
-- А листовое дерево выглядело бы: [[1, 2], 3]
instance Show a => Show (Tree a) where
    show EmptyTree = "_"
    show (TreeNode x1 EmptyTree EmptyTree) = show x1
    show (TreeNode x1 t1 t2) = (show x1) ++ "[" ++ show t1 ++ ", " ++ show t2 ++ "]"

instance Show a => Show (LeafTree a) where
    show (Leaf a) = show a
    show (InnerNode a b) = "[" ++ show a ++ ", " ++ show b ++ "]"
--    2 В сложной версии сделайте красивый вывод дерева, 
-- как в команде tree в linux:
--  1
--  ├── 2
--  │   ├── 5
--  │   └── 6
--  └── 3
--  └── 5

-- 2 Реализуйте для дерева функцию сворачивания. 
--Она принимает на вход две функции, соответствующие каждому из конструкторов: 
--f1 :: b 
--(для конструктора EmptyTree), 
--f2 :: a -> b -> b -> b 
--(для конструктора TreeNode, мы имеем 
--одно значение в узле и два свернутых значения из поддеревьев). 
--И еще она принимает на вход дерево
--а - значение древесное
--б - значение свёрнутое, получаемое получается из куска дерева, 
--         начиная от значения древесного
--data Tree a = EmptyTree | TreeNode a (Tree a) (Tree a) 
treeFold :: b -> (a -> b -> b -> b) -> Tree a -> b
treeFold f1 _ EmptyTree = f1
treeFold f1 f2 (TreeNode x1 t1 t2) = f2 x1 (treeFold f1 f2 t1) (treeFold f1 f2 t2)

-- 3 Аналогично, для листового дерева придумайте сигнатуру и 
-- реализуйте сворачивание.

-- 4 Для листового дерева реализуйте listifyLeafTree, превращение в список. 
-- росто значения из листьев выписываются слева направо. 
-- Вы можете использовать для этого реализованное выше сворачивание.

-- 5 Аналогично для обычного дерева реализуйте listifyTreeFirst, 
-- listifyTreeLast, listifyTreeBetween, это три разных превращения в список. 
-- Они отличаются тем, в какой момент корень дерева попадает в список. 
-- В начале, в конце или между тем как в список попадет левое поддерево, 
-- и до того, как попадет правое. 
-- Попробуйте начать писать превращение в список с помощью свораивания, 
-- чтобы понять, что имеется в виду.

-- 6 Имея listify для деревьев, сделайте их 
-- представителями класса Foldable.



-- Деревья поиска
-- 1 Дерево поиска. Представьте, что у нас есть правило для того, 
-- какие значения хранятся в дереве. Значение в узле всегда не меньше 
-- всех значений в левом поддереве, и всегда меньше всех значений в 
-- правом поддереве. Реализуйте функцию добавления нового значения в дерево.
-- Кстати, как вы опишете тип у такой функции? 
-- Вы можете для простоты считать, что дерево поиска это Tree Int, 
-- но лучше считайте, что это Ord a => Tree a, 
-- т.е. дерево из любого упорядоченного типа.

-- 2 Реализуйте функцию поиска минимального элемента в дереве поиска.

-- 3 Реализуйте функцию поиска и удаления минимального элемента 
-- в дереве поиска.