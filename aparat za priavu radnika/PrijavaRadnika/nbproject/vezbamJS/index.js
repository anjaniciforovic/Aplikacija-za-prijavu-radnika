console.log("vezba");

const niz = [
    { name: 'anja', age: '21', weight: 60 },
    { name: 'ana', age: '16', weight: 50 },
    { name: 'anastasija', age: '20', weight: 70 },
    { name: 'marko', age: '26', weight: 90 },
    { name: 'jovan', age: '36', weight: 85 },
    { name: 'milos', age: '66', weight: 100 },
];

let tezinaPrekoSedamdeset = niz.filter(p => p.weight > 70);


let imena = niz.map(p => p.name);

let kilaza = niz.reduce((total, p) => total + p.weight, 0);
console.log(kilaza);


const sortiranje = niz.sort((a, b) => a.age - b.age);

let pocetnoSlovoA = niz.filter(p=>p.name.startsWith('a'));

let preko21 = niz.filter(p => p.age >= 21 );

let smrsajuZa5Kg = niz.map(p => ({ ...p, weight: p.weight - 5 }) );

console.log(smrsajuZa5Kg);



function map(array, func) {
    const newArray = [];
    for (const element of array) {
        newArray.push(func(element));
    }
    return newArray;

}

function filter(array, func) {
    const newArray = [];

    for (const element of array) {
        const shouldInclude = func(element);

        if (shouldInclude) {
            newArray.push(element);
        }
    }

    return newArray;
}


function sort(array, func) {
    for (let i = 0; i < array.length - 1; i++) {
        for (let j = i + 1; j < array.length; j++) {
            if (func(array[i], array[j]) > 0) {
                let temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
    }

    return array;
}
