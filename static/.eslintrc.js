module.exports = {
  root: true,
  env: {
    browser: true,
    node: true,
  },
  parserOptions: {
    ecmaVersion: 2017,
  },
  env: {
    es6: true,
  },
  overrides: [
    {
      files: ["*.js"],
      extends: ["plugin:prettier/recommended"],
      rules: {
        "dot-notation": "off",
        "no-console": "off",
      },
    },
    {
      files: ["*.ts", "*.tsx"],
      extends: [
        "plugin:@typescript-eslint/recommended",
        "prettier/@typescript-eslint",
        "plugin:prettier/recommended",
      ],
      parser: "@typescript-eslint/parser",
      parserOptions: {
        sourceType: "module",
        ecmaFeatures: {
          legacyDecorators: true,
        },
        warnOnUnsupportedTypeScriptVersion: false,
      },
      rules: {
        "dot-notation": "off",
        "no-console": "off",
        "import/no-mutable-exports": "off",
        "@typescript-eslint/no-empty-interface": "off",
        "@typescript-eslint/no-object-literal-type-assertion": "off",
        "@typescript-eslint/interface-name-prefix": "off",
        "@typescript-eslint/no-unused-vars": "off",
        "@typescript-eslint/no-non-null-assertion": "off",
        "@typescript-eslint/no-explicit-any": "off",
        "@typescript-eslint/no-var-requires": "off",
        "@typescript-eslint/ban-ts-ignore": "off",
      },
    },
  ],
};
