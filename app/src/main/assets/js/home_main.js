var app = new Vue({
  el: "#app",
  data: {
    jiyiloading: true,
    upData: [{
      date: '2022-04-29',
      version: '0.1.0',
      info: '测试版本'
    }],
    folders: [{ id: 123, title: 456 }],
    newCard: {
      dialogVisible: false,
      title: '',
      text: ''
    }
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
    },
    newFolder() {
      layer.prompt({ title: '新建集合:请输入集合名', formType: 3 }, function (folderName, index) {
        layer.close(index);
        if (folderName.length > 500) {
          layer.msg('集合名过长（集合名不可超过500个字符）');
        } else if (folderName != "") {
          window.tyza66.pushFolder(folderName);
          layer.msg('集合' + folderName + '创建完成');
          window.app.folders = [];
          window.tyza66.pullFloder2();
        }
        else {
          layer.msg('请输入集合名');
        }
      });
    },
    deleteFolder() {
      layer.prompt({ title: '删除集合:请输入集合右侧蓝色编号', formType: 3 }, function (folderId, index) {
        layer.close(index);
        var code = window.tyza66.deleteFolder(folderId);
        if (code = "1") {
          layer.msg('集合' + folderId + '已被删除');
          window.app.folders = [];
          window.tyza66.pullFloder2();
        } else {
          layer.msg('删除失败，请检查集合是否存在');
        }
      });
    },
    handleClose(done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done();
        })
        .catch(_ => { });
    },
    createNewCard(id) {
      console.log(123);
      this.newCard.dialogVisible = true;
    }
  }
});