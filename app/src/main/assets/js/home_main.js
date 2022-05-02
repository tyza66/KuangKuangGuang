var app = new Vue({
  el: "#app",
  data: {
    jiyiloading: true,
    versionData: [{
      date: '2022-04-29',
      version: '0.1.0',
      info: '测试版本'
    }, {
      date: '2022-05-02',
      version: '0.1.1',
      info: '更新了集合操作'
    }],
    folders: [],
    newCard: {
      dialogVisible: false,
      title: '',
      text: '',
      nowId: ''
    },
    manageCards: {
      dialogVisible: false,
      nowId: ''
    },
    cardData: [{
      title: 'g132323',
      text: '344111',
      statu: '111'
    }],
    showTitle: true,
    showText: true
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
          this.newCard.title = '';
          this.newCard.text = '';
          done();
        })
        .catch(_ => { });
    },
    createNewCard(id) {
      this.newCard.nowId = id;
      this.newCard.dialogVisible = true;
    },
    cancalNewCard() {
      this.newCard.title = '';
      this.newCard.text = '';
      this.newCard.dialogVisible = false;
    },
    pushNewCard() {
      if (this.newCard.title == "") {
        layer.msg('标题不可为空');
      } else if (this.newCard.title.length > 100) {
        layer.msg('卡片标题过长（卡片标题不可超过100个字符）');
      } else if (this.newCard.text.length > 500) {
        layer.msg('卡片内容过长（卡片内容不可超过500个字符）');
      } else {
        window.tyza66.pushCard(this.newCard.nowId, this.newCard.title, this.newCard.text);
        this.newCard.title = '';
        this.newCard.text = '';
        this.newCard.dialogVisible = false;
        this.cardData = [];
        window.tyza66.pullCards(id);
        layer.msg('卡片已创建');
      }
    },
    manageCard(id) {
      this.manageCards.nowId = id;
      this.cardData = [];
      window.tyza66.pullCards(this.manageCards.nowId);
      this.manageCards.dialogVisible = true;
    },
    deleteCard(card) {
      this.$confirm('此操作将永久删除该卡片, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        window.tyza66.deleteCard(this.manageCards.nowId, card.title, card.text);
        this.cardData = [];
        window.tyza66.pullCards(this.manageCards.nowId);
        this.$message({
          type: 'success',
          message: '删除成功!'
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    appendCardData(title, text, statu) {
      var one = {
        title: title,
        text: text,
        statu: statu
      }
      this.cardData.push(one);
    },
    useFolder(id) {
      this.cardData = [];
      window.tyza66.pullCards(id);
      this.$message({
        type: 'success',
        message: '启用成功!'
      });
    }
  }
});