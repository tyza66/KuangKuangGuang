var app = new Vue({
  el: "#app",
  data: {
    jiyiloading: true,
    upData: [{
      date: '2022-04-29',
      version: '0.1.0',
      info: '测试版本'
    }],
    folders: []
  },
  created: function () {
    this.welcome();
    //window.tyza66.test();
    this.jiyiloading = false;
  },
  methods: {
    welcome() {
      const h = this.$createElement;
      this.$notify({
        title: '欢迎使用',
        message: h('i', { style: 'color: teal' }, '欢迎使用框哐咣卡片记忆软件，看看主页的使用教程帮助你快速上手！')
      });
    },
    appendFolder(date, id) {
      var one = {
        id: date,
        title: id
      };
      this.folders.push(one);
    }
  }
});