const path = require('path');
const fs = require('fs');

const solc = require('solc');

const lotteryPath = path.resolve(__dirname, 'contracts', 'Lottery.sol');
const source = fs.readFileSync(lotteryPath, 'utf-8');

var input = {
  language: 'Solidity',
  sources: {
    'Lottery.sol': {
      content: source,
    },
  },
  settings: {
    outputSelection: {
      '*': {
        '*': ['*'],
      },
    },
  },
};

// stupid required callback I dont currently understand... see documentation
function findImports(path) {
  return;
}

// New syntax (supported from 0.5.12, mandatory from 0.6.0)
var output = JSON.parse(
  solc.compile(JSON.stringify(input), { import: findImports }),
);

const interface = output.contracts['Lottery.sol'].Lottery.abi;
const bytecode = output.contracts['Lottery.sol'].Lottery.evm.bytecode.object;

// export the inbox contract object
module.exports = { interface, bytecode };
