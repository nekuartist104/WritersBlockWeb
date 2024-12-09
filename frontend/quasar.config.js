const { configure } = require('quasar/wrappers')

module.exports = configure(function (/* ctx */) {
    return {
        eslint: {
            fix: true,
            warnings: true,
            errors: true
        },
        boot: ['axios'],
        css: ['app.scss', 'quasar-defaults.scss'],
        extras: [
            'fontawesome-v6',
            'material-icons'
        ],
        build: {
            target: {
                browser: ['es2019', 'edge88', 'firefox78', 'chrome87', 'safari13.1']
            },
            vueRouterMode: 'history'
        },
        devServer: {
            proxy: {
                '/api': {
                    target: 'http://localhost:8080',
                    ws: true,
                    changeOrigins: true
                }
            },
            overlay: {
                warnings: true,
                errors: true
            },
            vueDevTools: true
        },
        framework: {
            config: {
                notify: {
                },
                dialog: {},
                loadingBar: {}
            },
            plugins: ['Notify', 'Dialog', 'LocalStorage', 'SessionStorage']
        },
        animations: ['fadeInUp', 'fadeOutDown'],
        ssr: {
            pwa: false,
            prodPort: 3000,
            middlewares: [
                'render'
            ]
        },
        pwa: {
            workboxMode: 'generateSW',
            injectPwaMetaTags: true,
            swFilename: 'sw.js',
            manifestFilename: 'manifest.json',
            useCredentialsForManifestTag: false
        },
        cordova: {
        },
        capacitor: {
            hideSplashscreen: true
        },
        electron: {
            inspectPort: 5858,
            bundler: 'packager',
            packager: {
            },
            builder: {
                appId: 'frontend'
            }
        },
        bex: {
            contentScripts: ['my-content-script']
        }
    }
})
