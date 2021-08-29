'use strict';

const doExamples = sum => {
  console.log(`1 + 2 + 3 = ${sum(1, 2, 3)}`);
  console.log(`0 = ${sum(0)}`);
  console.log(`undefined  = ${sum()}`);
  console.log(`1 - 1 + 1 = ${sum(1, -1, 1)}`);
  console.log(`10 - 1 - 1 - 1 = ${sum(10, -1, -1, -1)}`);
};


{
  const sum = (...args) => {
    let res = 0;
    for (let i = 0; i < args.length; i++) {
      res += args[i];
    }
    return res;
  };

  console.log();
  console.log('for');
  console.log(doExamples(sum));
}


{
  const sum = (...args) => {
    let res = 0;
    for (const num of args) {
      res += num;
    }
    return res;
  };

  console.log();
  console.log('for..of');
  console.log(doExamples(sum));
}


{
  const sum = (...args) => {
    let res = 0;
    let i = 0;
    while (i !== args.length) {
      res += args[i];
      i += 1;
    }
    return res;
  };

  console.log();
  console.log('while');
  console.log(doExamples(sum));
}


{
  const sum = (...args) => {
    let res = 0;
    let i = 0;
    do {
      res += args[i];
      i += 1;
    } while (i < args.length);
    return res ? res : 0;
  };

  console.log();
  console.log('do..while');
  console.log(doExamples(sum));
}


{
  const sum = (...args) => {
    const res = args.reduce((acc, num) => acc + num, 0);
    return res;
  };

  console.log();
  console.log('Array.prototype.reduce()');
  console.log(doExamples(sum));
}
