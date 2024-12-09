/* eslint-disable */

module.exports = {
    plugins: [
        require('autoprefixer')({
            overrideBrowserslist: [
                'last 4 Chrome versions',
                'last 4 FireFox versions',
                'last 4 Edge versions',
                'last 4 Safari versions',
                'last 4 Android versions',
                'last 4 ChromeAndroid versions',
                'last 4 FireFoxAndroid versions',
                'last 4 iOS versions',
            ]
        })
    ]
}
