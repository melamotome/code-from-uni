//random(min, max)
'use strict';

const random = (min, max) => {
  if (!max) {
    return random(0, min);
  }
  return Math.floor(Math.random() * (max - min + 1)) + min;
};

console.log(`Random 8 to 43 = ${random(8, 43)}`);
console.log(`Random 0 to 10 = ${random(10)}`);

//generateKey(length, characters)
const generateKey = (length, characters) => {
  let l = '';
  for (let i = 0; i < length; i++)
    l += characters[random(length)];
  return l;
};

const characters = 'abcdefghijklmnopqrstuvwxyz0123456789';
const key = generateKey(16, characters);

console.log(key);
