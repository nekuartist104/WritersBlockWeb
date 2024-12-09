module.exports = {
    root: true,
    parserOptions: {
        parser: require.resolve('@typescript-eslint/parser'),
        extraFileExtensions: ['.vue']
    },
    env: {
        browser: true,
        es2021: true,
        node: true,
        'vue/setup-compiler-macros': true
    },
    extends: [
        'plugin:@typescript-eslint/recommended',
        'plugin:vue/vue3-recommended',
        'prettier'
    ],
    globals: {
        cordova: 'readonly',
        __statics: 'readonly',
        __QUASAR_SSR__: 'readonly',
        __QUASAR_SSR_SERVER__: 'readonly',
        __QUASAR_SSR_CLIENT__: 'readonly',
        __QUASAR_SSR_PWA__: 'readonly',
        process: 'readonly',
        Capacitor: 'readonly',
        chrome: 'readonly',
    },
    rules: {
        curly: 0,
        'no-lonely-if': 1,
        'no-mixed-requires': 0,
        'no-underscore-dangle': 0,
        '@typescript-eslint/no-unused-vars': [2, { vars: 'all', args: 'after-used' }],
        'no-use-before-define': [2, 'nofunc'],
        semi: [2, 'never'],
        'keyword-spacing': 0,
        'space-infix-ops': 0,
        strict: 0,
        'prefer-promise-reject-errors': 'off',
        quotes: [1, 'single', { avoidEscape: true }],
        '@typescript-eslint/explicit-function-return-type': 'off',
        '@typescript-eslint/no-var-requires': 'off',
        '@typescript-eslint/no-non-null-assertionss': 'off',
        'no-debugger': process.env.NODE_ENV === 'production' ? 'error' : 'off'
    }
}
