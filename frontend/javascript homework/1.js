'use strict';

//Две реализации функции `inc`:
{
  const inc = n => (++n);
  const a = 5;
  const b = inc(a);
  console.dir({ a, b });
}


{
  const inc = num => (++num.n);
  const obj = { n: 5 };
  console.dir(obj);
  inc(obj);
  console.dir(obj);
}

//Подсчёт элементов различных типов в массиве
const arr = [true, 'hello', 5, 12.33, -200, false, 'word', { a: 3, b: 4, c: 5 }, [1, 2, 3], null, -102.123, undefined];
console.dir(arr);

{
  const hash = {number: 0, string: 0, boolean: 0, undefined: 0, object: 0};
  for (const value of arr) {
    const type = typeof value;
    hash[type] += 1;
  }
  console.dir(hash);
}


{
  const hash = {};
  for (const value of arr) {
    const type = typeof value;
    hash[type] = type in hash ? hash[type] + 1 : 1;
  }
  console.dir(hash);
}
