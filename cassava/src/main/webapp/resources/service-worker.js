if(!self.define){let s,e={};const r=(r,a)=>(r=new URL(r+".js",a).href,e[r]||new Promise((e=>{if("document"in self){const s=document.createElement("script");s.src=r,s.onload=e,document.head.appendChild(s)}else s=r,importScripts(r),e()})).then((()=>{let s=e[r];if(!s)throw new Error(`Module ${r} didn’t register its module`);return s})));self.define=(a,o)=>{const c=s||("document"in self?document.currentScript.src:"")||location.href;if(e[c])return;let n={};const i=s=>r(s,c),f={module:{uri:c},exports:n,require:i};e[c]=Promise.all(a.map((s=>f[s]||i(s)))).then((s=>(o(...s),n)))}}define(["./workbox-5b385ed2"],(function(s){"use strict";s.setCacheNameDetails({prefix:"template"}),self.addEventListener("message",(s=>{s.data&&"SKIP_WAITING"===s.data.type&&self.skipWaiting()})),s.precacheAndRoute([{url:"/cassava/css/app.e6739e91.css",revision:null},{url:"/cassava/img/hero-bg.dfe9ba34.jpg",revision:null},{url:"/cassava/index.html",revision:"38c1241fcaf4e4daedb107a4e036c676"},{url:"/cassava/js/app.1a663937.js",revision:null},{url:"/cassava/js/chunk-vendors.115d3c60.js",revision:null},{url:"/cassava/manifest.json",revision:"b400fd73d650f2a6a929915212958bf9"},{url:"/cassava/resources/frontend/css/detail.css",revision:"9800d28fe917149ddd50631eb4187e9c"},{url:"/cassava/resources/frontend/css/select.css",revision:"e3ba9aba9e5e4958b65b5664bd903c8c"},{url:"/cassava/resources/frontend/css/style.css",revision:"0161813fdb3a5b1c3317c52ca457a455"},{url:"/cassava/resources/frontend/img/about.jpg",revision:"499eaf2440fd7f9054cc32db249beeb5"},{url:"/cassava/resources/frontend/img/apple-touch-icon.png",revision:"6eba19e687e2107a8216f76b1ea1ea29"},{url:"/cassava/resources/frontend/img/blog/blog-1.jpg",revision:"104a9df65e5d7fab75ece29d5d836c2f"},{url:"/cassava/resources/frontend/img/blog/blog-2.jpg",revision:"033112e1fcd8439b282875bf51869bca"},{url:"/cassava/resources/frontend/img/blog/blog-3.jpg",revision:"5125d55a8fbffcb7c75ca68d2eab223d"},{url:"/cassava/resources/frontend/img/blog/blog-4.jpg",revision:"2dbf8fdc547538f6e1f296948a669f54"},{url:"/cassava/resources/frontend/img/blog/blog-author.jpg",revision:"ef42f8e1a4420973a82dcd6317782dd2"},{url:"/cassava/resources/frontend/img/blog/blog-inside-post.jpg",revision:"e1d7fedfedc5cc19fc7706e5462cf6cf"},{url:"/cassava/resources/frontend/img/blog/blog-recent-1.jpg",revision:"0e8571f16a059748a04fec39f30a1c69"},{url:"/cassava/resources/frontend/img/blog/blog-recent-2.jpg",revision:"b13e3dc2831f733c246cbf4169da9bde"},{url:"/cassava/resources/frontend/img/blog/blog-recent-3.jpg",revision:"7adff20e23626c54d6206e29face7f2f"},{url:"/cassava/resources/frontend/img/blog/blog-recent-4.jpg",revision:"8da8efda9c9df720b8b48de52bfdf2c6"},{url:"/cassava/resources/frontend/img/blog/blog-recent-5.jpg",revision:"5125d55a8fbffcb7c75ca68d2eab223d"},{url:"/cassava/resources/frontend/img/blog/comments-1.jpg",revision:"c38e7ac9d2e31b6d7b1466505ba107a2"},{url:"/cassava/resources/frontend/img/blog/comments-2.jpg",revision:"22c9ae40fa9ab3893d50c9ac3725a89a"},{url:"/cassava/resources/frontend/img/blog/comments-3.jpg",revision:"c7beb9734e5b7001f41bc4328d5ef6ca"},{url:"/cassava/resources/frontend/img/blog/comments-4.jpg",revision:"4396223dba1bc74de120c8453cc18eec"},{url:"/cassava/resources/frontend/img/blog/comments-5.jpg",revision:"b8e0d3f0967d33d1c65acceff113ac79"},{url:"/cassava/resources/frontend/img/blog/comments-6.jpg",revision:"a2530c62a2e4b6c212c832b0486cc7ee"},{url:"/cassava/resources/frontend/img/favicon.png",revision:"6eba19e687e2107a8216f76b1ea1ea29"},{url:"/cassava/resources/frontend/img/features-1.svg",revision:"52980d42775d44be0f030f0616dc88f5"},{url:"/cassava/resources/frontend/img/features-2.svg",revision:"529facd4cee1d1f9f4d79370a555049e"},{url:"/cassava/resources/frontend/img/features-3.svg",revision:"cf0b6b88896bb450bb94684ecaf35f74"},{url:"/cassava/resources/frontend/img/features-4.svg",revision:"a961f4486cba78098f0bb17e5397259c"},{url:"/cassava/resources/frontend/img/hero-bg.jpg",revision:"c5daca9a0e337f6181db269b74081c7d"},{url:"/cassava/resources/frontend/img/portfolio/portfolio-1.jpg",revision:"f67d37b06c5a8bf729742801a6066c7a"},{url:"/cassava/resources/frontend/img/portfolio/portfolio-2.jpg",revision:"381bd1127478827677394b97e2e8269d"},{url:"/cassava/resources/frontend/img/portfolio/portfolio-3.jpg",revision:"81474c83b4cbbabe654d6b62035e87b9"},{url:"/cassava/resources/frontend/img/portfolio/portfolio-4.jpg",revision:"7031e8e37cd0a68cac8502974339385a"},{url:"/cassava/resources/frontend/img/portfolio/portfolio-5.jpg",revision:"f275fd26c42a5ba420f42d408d1f5e2d"},{url:"/cassava/resources/frontend/img/portfolio/portfolio-6.jpg",revision:"0c46aa93be520dcda0d37d49f2e2b640"},{url:"/cassava/resources/frontend/img/portfolio/portfolio-7.jpg",revision:"b23326931c9cda6bff1f34d9e92b6d7d"},{url:"/cassava/resources/frontend/img/portfolio/portfolio-8.jpg",revision:"df1afa14393481756464f9fbcd72e551"},{url:"/cassava/resources/frontend/img/portfolio/portfolio-9.jpg",revision:"184fa0b5cd8b2e31671598ef2d253b23"},{url:"/cassava/resources/frontend/img/portfolio/portfolio-details-1.jpg",revision:"68f22bbbf2e80be8ffed4b926be1b2ff"},{url:"/cassava/resources/frontend/img/portfolio/portfolio-details-2.jpg",revision:"1ff8dc618f3969a9c019c7e1103fe7bf"},{url:"/cassava/resources/frontend/img/portfolio/portfolio-details-3.jpg",revision:"d6af0938f5fc9e840f034ab74cbf9ee1"},{url:"/cassava/resources/frontend/img/service-details-1.jpg",revision:"cfbaca4ebf8943659ce10c8b5f6896e8"},{url:"/cassava/resources/frontend/img/service-details-2.jpg",revision:"aad6ba4b2b0b46bc66b1b419e3c00936"},{url:"/cassava/resources/frontend/img/service-details-3.jpg",revision:"1192a86d7075a15ba491f01cebceca0a"},{url:"/cassava/resources/frontend/img/service-details-4.jpg",revision:"2a731fd8e995a93d98f1b85f959d4ad8"},{url:"/cassava/resources/frontend/img/testimonials/testimonials-1.jpg",revision:"2eb96321ab48d0ba8c87de31ad7087fe"},{url:"/cassava/resources/frontend/img/testimonials/testimonials-2.jpg",revision:"b6d1701868336600a333ea7bec662425"},{url:"/cassava/resources/frontend/img/testimonials/testimonials-3.jpg",revision:"3d677828b23ca5dc9653eb9e8f92aa1f"},{url:"/cassava/resources/frontend/img/testimonials/testimonials-4.jpg",revision:"08d8679b1e421ad63fd4d61156a5acd1"},{url:"/cassava/resources/frontend/img/testimonials/testimonials-5.jpg",revision:"72fb04435870ed091240bd9da7474dab"},{url:"/cassava/resources/frontend/img/why-us.jpg",revision:"b13e3dc2831f733c246cbf4169da9bde"},{url:"/cassava/resources/frontend/js/main.js",revision:"ae200fb1e5f8f2c3d42b8be892061c5a"},{url:"/cassava/resources/frontend/scss/Readme.txt",revision:"ec8d7cb9948de9dd3251c3075aa66271"},{url:"/cassava/resources/frontend/vendor/animate.css/animate.compat.css",revision:"9bb18ac93ea35cc03e71691c4af09568"},{url:"/cassava/resources/frontend/vendor/animate.css/animate.css",revision:"cf2741a3a7ea8427ade651533a54ef1b"},{url:"/cassava/resources/frontend/vendor/animate.css/animate.min.css",revision:"c0be8e53226ac34833fd9b5dbc01ebc5"},{url:"/cassava/resources/frontend/vendor/aos/aos.css",revision:"847da8fca8060ca1a70f976aab1210b9"},{url:"/cassava/resources/frontend/vendor/aos/aos.js",revision:"d3718e34eeb0355be8e3179a2e2bccb7"},{url:"/cassava/resources/frontend/vendor/bootstrap-icons/bootstrap-icons.css",revision:"06cb502613f99040e534fec65fa725c7"},{url:"/cassava/resources/frontend/vendor/bootstrap-icons/bootstrap-icons.json",revision:"1151e9ccf9088de9ce5efe585c4c3f92"},{url:"/cassava/resources/frontend/vendor/bootstrap-icons/bootstrap-icons.scss",revision:"9445001ef8462a9d0afc38ed99233461"},{url:"/cassava/resources/frontend/vendor/bootstrap-icons/fonts/bootstrap-icons.woff",revision:"52196284de1fcb5b044f001a75482dba"},{url:"/cassava/resources/frontend/vendor/bootstrap-icons/fonts/bootstrap-icons.woff2",revision:"7f477633ddd12f84284654f2a2e89b8a"},{url:"/cassava/resources/frontend/vendor/bootstrap/css/bootstrap-grid.css",revision:"02c04dfa80af659dc6f4c517b677435d"},{url:"/cassava/resources/frontend/vendor/bootstrap/css/bootstrap-grid.min.css",revision:"dbd47382523d754013115de4be202a74"},{url:"/cassava/resources/frontend/vendor/bootstrap/css/bootstrap-grid.rtl.css",revision:"63d1e5a2903e394f52b1fccaf84159a0"},{url:"/cassava/resources/frontend/vendor/bootstrap/css/bootstrap-grid.rtl.min.css",revision:"92871a500cb2d82f99258a6a17e46ef6"},{url:"/cassava/resources/frontend/vendor/bootstrap/css/bootstrap-reboot.css",revision:"28372dcca49ddee994668db39a49f7f0"},{url:"/cassava/resources/frontend/vendor/bootstrap/css/bootstrap-reboot.min.css",revision:"7b3e39ea9e950f59c494f3e0ae5971db"},{url:"/cassava/resources/frontend/vendor/bootstrap/css/bootstrap-reboot.rtl.css",revision:"d7cfce563ed23132808a3647f675a1ae"},{url:"/cassava/resources/frontend/vendor/bootstrap/css/bootstrap-reboot.rtl.min.css",revision:"1a3cae87f043a9031675fab697888c7c"},{url:"/cassava/resources/frontend/vendor/bootstrap/css/bootstrap-utilities.css",revision:"a5f78ee119a023227eceb749f83f6b12"},{url:"/cassava/resources/frontend/vendor/bootstrap/css/bootstrap-utilities.min.css",revision:"5e47a49091ab986da0c9a5122a5dfe6c"},{url:"/cassava/resources/frontend/vendor/bootstrap/css/bootstrap-utilities.rtl.css",revision:"a3ff7a01905bed4e279995549711d424"},{url:"/cassava/resources/frontend/vendor/bootstrap/css/bootstrap-utilities.rtl.min.css",revision:"1200ba112673d97391e77f097d1eb26f"},{url:"/cassava/resources/frontend/vendor/bootstrap/css/bootstrap.css",revision:"41ba0ff5eed842d853aede220a3ccfee"},{url:"/cassava/resources/frontend/vendor/bootstrap/css/bootstrap.min.css",revision:"3f30c2c47d7d23c7a994db0c862d45a5"},{url:"/cassava/resources/frontend/vendor/bootstrap/css/bootstrap.rtl.css",revision:"1457707e717950e48e9af2ef614b68e8"},{url:"/cassava/resources/frontend/vendor/bootstrap/css/bootstrap.rtl.min.css",revision:"dfa5ca983e2834131c9d9d51ae3b1eb2"},{url:"/cassava/resources/frontend/vendor/bootstrap/js/bootstrap.bundle.js",revision:"01a034c34cb9c1d2f062af8def13ecb7"},{url:"/cassava/resources/frontend/vendor/bootstrap/js/bootstrap.bundle.min.js",revision:"b75ae000439862b6a97d2129c85680e8"},{url:"/cassava/resources/frontend/vendor/bootstrap/js/bootstrap.esm.js",revision:"f86c449a0babc30b33ff71a6fd064833"},{url:"/cassava/resources/frontend/vendor/bootstrap/js/bootstrap.esm.min.js",revision:"da74cf4659eb6c671e549aaed3d7ca1d"},{url:"/cassava/resources/frontend/vendor/bootstrap/js/bootstrap.js",revision:"1376378024397729b1febb40f5a0e16f"},{url:"/cassava/resources/frontend/vendor/bootstrap/js/bootstrap.min.js",revision:"b0794583ec020a7852f0fc04d5cefc52"},{url:"/cassava/resources/frontend/vendor/boxicons/css/animations.css",revision:"2c0319e25c5cb233f4c6cf5e69d83fa3"},{url:"/cassava/resources/frontend/vendor/boxicons/css/boxicons.css",revision:"6beebb34ea7a1e8d446d98a9b2c0bf8d"},{url:"/cassava/resources/frontend/vendor/boxicons/css/boxicons.min.css",revision:"886ed8dd06c506c77cf226f4506b3c00"},{url:"/cassava/resources/frontend/vendor/boxicons/css/transformations.css",revision:"d5afab8d6fa009e5bf06744ef93003f1"},{url:"/cassava/resources/frontend/vendor/boxicons/fonts/boxicons.eot",revision:"4002d1c83d8dd40df85708c5601993e4"},{url:"/cassava/resources/frontend/vendor/boxicons/fonts/boxicons.svg",revision:"b0bb967778275b356010f01219188eb0"},{url:"/cassava/resources/frontend/vendor/boxicons/fonts/boxicons.ttf",revision:"878061312a371566f591f1f1a9f76a87"},{url:"/cassava/resources/frontend/vendor/boxicons/fonts/boxicons.woff",revision:"3a0cb82447f8e9ce865a709a92f0c752"},{url:"/cassava/resources/frontend/vendor/boxicons/fonts/boxicons.woff2",revision:"aab73283f839e775f9ac86d642983653"},{url:"/cassava/resources/frontend/vendor/glightbox/css/glightbox.css",revision:"76e8ba51c713846b57e22b321dfd7a63"},{url:"/cassava/resources/frontend/vendor/glightbox/css/glightbox.min.css",revision:"9b438b29cef1c212d1c65a877ffc7232"},{url:"/cassava/resources/frontend/vendor/glightbox/css/plyr.css",revision:"a39f7b91915f8ed00dd4e3e11a3c93eb"},{url:"/cassava/resources/frontend/vendor/glightbox/css/plyr.min.css",revision:"72c244ef068825d17123de804e1880b0"},{url:"/cassava/resources/frontend/vendor/glightbox/js/glightbox.js",revision:"7bafdeb041b5a6eac144d1cfefe01b07"},{url:"/cassava/resources/frontend/vendor/glightbox/js/glightbox.min.js",revision:"2b4c8cbaade24ecb58bcb0d89694ccee"},{url:"/cassava/resources/frontend/vendor/isotope-layout/isotope.pkgd.js",revision:"8896e082b3fa1738e2e2f558a7fc1fa4"},{url:"/cassava/resources/frontend/vendor/isotope-layout/isotope.pkgd.min.js",revision:"2afcff647ed260006faa71c8e779e8d4"},{url:"/cassava/resources/frontend/vendor/php-email-form/validate.js",revision:"942e0d365d33a0f2d2b6ebfbe9321ba1"},{url:"/cassava/resources/frontend/vendor/purecounter/purecounter_vanilla.js",revision:"306b61cceb925965f85d9b377f1539ad"},{url:"/cassava/resources/frontend/vendor/swiper/swiper-bundle.min.css",revision:"04720c60bc020cbba92785dd4029f7d2"},{url:"/cassava/resources/frontend/vendor/swiper/swiper-bundle.min.js",revision:"24fd8f796609d79fcb7b6e5ae754433b"},{url:"/cassava/resources/frontend/vendor/waypoints/noframework.waypoints.js",revision:"8c601d5f892e9a29c3dd204025f9d724"},{url:"/cassava/resources/img/DOA.png",revision:"f1e174d7a1b21b71061923598cdfbedb"},{url:"/cassava/resources/img/Facultyofkaset.png",revision:"9cd184451a5629f433e72e7130d89ebc"},{url:"/cassava/resources/img/KU_Logo.png",revision:"269170c3448157eb13c4b527fae4aee8"},{url:"/cassava/resources/img/NECTEC.png",revision:"aa1d8740beddc689c0d53bc7a9e00fe6"},{url:"/cassava/resources/img/autsadawut.jpg",revision:"4d65fa39e1cadab38f99d1f782c4146b"},{url:"/cassava/resources/img/chanakan.jpg",revision:"5c61bffe9c5c00d0abc9d6b5f5eafe7e"},{url:"/cassava/resources/img/kaset.png",revision:"77a7da69d56bcdabf6390e5f04307f23"},{url:"/cassava/resources/img/kaset_department.png",revision:"873b0188884a7f2faaa77906173cbe36"},{url:"/cassava/resources/img/kittipat.jpg",revision:"668c6b3857162d264b75f423a22421a0"},{url:"/cassava/resources/img/mobile1.jpg",revision:"408a64505a29d616aab99ca6b54824ef"},{url:"/cassava/resources/img/mobile2.jpg",revision:"dc3b765d2009003e36ff91d6b6b8d130"},{url:"/cassava/resources/img/mobile3.jpg",revision:"014de5b1eb208e10252c1f0959c437b3"},{url:"/cassava/resources/img/mobile4.jpg",revision:"1eb25c298b3aba65eb4e2062a7dcb99d"},{url:"/cassava/resources/img/mobile5.jpg",revision:"b3d7fb2a279a3aec8553ce84c9800fc6"},{url:"/cassava/resources/img/no-image.jpg",revision:"c300853f57a167e6553effdcb160c5d3"},{url:"/cassava/resources/img/panuwat.jpg",revision:"a4fe1fc66f52bec3bf3ff65636a75abb"},{url:"/cassava/resources/img/patipan.jpg",revision:"f988cb96ea96e241b8bd809b5813543a"},{url:"/cassava/resources/img/pattarapol.jpg",revision:"77f661339ff24b45e84c592bfe77a77f"},{url:"/cassava/resources/img/paweenwich.jpg",revision:"0bbbca5a1668571a8097314772736d18"},{url:"/cassava/resources/img/peerawat.jpg",revision:"eafe334fb391461a8ad348355aefe87f"},{url:"/cassava/resources/img/phattaraporn.jpg",revision:"fe3ddb24d71cd2b0a2e9c2ef8f374766"},{url:"/cassava/resources/img/prawee.jpg",revision:"59f1b8728799f0b79381dea2fb82441b"},{url:"/cassava/resources/img/rojjanakorn.jpg",revision:"3009b3ff4f9e924ee68cd601f3ab835a"},{url:"/cassava/resources/img/sasicha.jpg",revision:"3e2eec59fa0b9bf270dd516d40e493b3"},{url:"/cassava/resources/img/theerapat.jpg",revision:"9bc0278ded7dbf8b3da485460e03a652"},{url:"/cassava/resources/img/webimg.jpg",revision:"8c9e712efaec8abfcbd7e0099ba0e39a"},{url:"/cassava/robots.txt",revision:"b6216d61c03e6ce0c9aea6ca7808f7ca"}],{})}));
//# sourceMappingURL=service-worker.js.map
