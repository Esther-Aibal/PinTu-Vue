<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="图集名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入图集名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="所属地图" prop="mapId">
        <el-select v-model="queryParams.mapId" placeholder="请选择地图" clearable size="small">
          <el-option
            v-for="map in mapOptions"
            :key="map.id"
            :label="map.name"
            :value="map.name"
          />
        </el-select>

      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择状态" clearable size="small">
          <el-option
            v-for="dict in abStatusOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
        <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['ab:atlas:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['ab:atlas:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['ab:atlas:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['ab:atlas:export']"
        >导出</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="atlasList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="所属地图" align="center" prop="mapId" :formatter="mapFormat"/>
      <el-table-column label="图集名称" align="center" prop="name"  />
      <el-table-column label="行数" align="center" prop="row" />
      <el-table-column label="列数" align="center" prop="cel" />
      <el-table-column label="图片总数" align="center" prop="total" />
      <el-table-column label="状态" align="center" prop="status" :formatter="statusFormat"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['ab:atlas:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['ab:atlas:remove']"
          >删除</el-button>
          <el-button v-show="scope.row.status ==0"
                     size="mini"
                     type="text"
                     icon="el-icon-view"
                     @click="handleReview(scope.row)"
                     v-hasPermi="['ab:picture:edit']"
          >审核</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改图集对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="图集名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入图集名称" />
        </el-form-item>
        <el-form-item label="地图" prop="mapId">
          <el-radio-group v-model="form.mapId">
            <el-radio
              v-for="map in mapOptions"
              :key="map.id"
              :label="map.id"
            >{{map.name}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="图片地址" prop="imgUrl">
          <img v-bind:src="form.imgUrl" class="img-circle img-lg" />
          <el-upload :show-file-list="false"
                     :before-upload="beforeUpload"
                     :action="uploadImgUrl"
                     name="file"
                     :headers="headers"
                     :on-success="quillImgSuccess">
            <el-button size="small">
              上传
              <i class="el-icon-upload el-icon--right"></i>
            </el-button>
          </el-upload>
        </el-form-item>
        <el-form-item label="行数" prop="total">
          <el-input v-model="form.row" placeholder="请输入行数" />
        </el-form-item>
        <el-form-item label="列数" prop="total">
          <el-input v-model="form.cel" placeholder="请输入列数" />
        </el-form-item>
        <el-form-item label="图片总数" prop="total">
          <el-input v-model="form.total" placeholder="请输入图片总数" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listAtlas, getAtlas, delAtlas, addAtlas,review, updateAtlas, exportAtlas,getMaps } from "@/api/ab/atlas";
import { getToken } from '@/utils/auth'
export default {
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 总条数
      total: 0,
      // 图集表格数据
      atlasList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        mapId: undefined,
        name: undefined,
        status: undefined,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        mapId: [
          { required: true, message: "图集id不能为空", trigger: "blur" }
        ],
        name: [
          { required: true, message: "名称不能为空", trigger: "blur" }
        ],
        total: [
          { required: true, message: "总数不能为空", trigger: "blur" }
        ],
        row: [
          { required: true, message: "行数不能为空", trigger: "blur" }
        ],
        cel: [
          { required: true, message: "列数不能为空", trigger: "blur" }
        ],
        type: [
          { required: true, message: "所属地图不能为空", trigger: "blur" }
        ],
      },
      mapOptions:[],
      abStatusOptions:[],
      uploadImgUrl: process.env.VUE_APP_BASE_API + "/common/upload", // 上传的图片服务器地址
      headers: {
        Authorization: 'Bearer ' + getToken()
      }
    };
  },
  created() {

    this.getList();
    getMaps().then(response => {
      this.mapOptions =response.data;
    });
    this.getDicts("ab_status").then(response => {
      this.abStatusOptions = response.data;
    });

  },
  methods: {
    /** 查询图集列表 */
    getList() {
      this.loading = true;
      listAtlas(this.queryParams).then(response => {
        this.atlasList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 字典状态字典翻译
    statusFormat(row, column) {
      return this.selectDictLabel(this.abStatusOptions, row.status);
    },
    // 字典状态字典翻译
    mapFormat(row, column) {
      console.log(row.mapId);
      var actions = [];
      Object.keys(this.mapOptions).map((key) => {
        console.log(this.mapOptions[key].id+"相同"+row.mapId);
        if (this.mapOptions[key].id == ( row.mapId)) {
          console.log("相同");
          actions.push(this.mapOptions[key].name);
          return actions.join('');
        }
      })
      return actions.join('');
    },
    // 表单重置
    reset() {
      this.form = {
        id: undefined,
        imgUrl: undefined,
        mapId: undefined,
        name: undefined,
        status: "0",
        total: undefined,
        delFlag: undefined,
        createTime: undefined,
        createBy: undefined,
        updateTime: undefined,
        updateBy: undefined
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!=1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加图集";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getAtlas(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改图集";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            updateAtlas(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              } else {
                this.msgError(response.msg);
              }
            });
          } else {
            addAtlas(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              } else {
                this.msgError(response.msg);
              }
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除图集编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delAtlas(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 审核按钮操作 */
    handleReview(row) {
      const id = row.id;
      this.$confirm('是否确认通过审核?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function() {
        return review(id);
      }).then(() => {
        this.getList();
        this.msgSuccess("审核成功");
      }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有图集数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportAtlas(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    },
    // 上传预处理
    beforeUpload(file) {
      if (file.type.indexOf("image/") == -1) {
        this.msgError("文件格式错误，请上传图片类型,如：JPG，PNG后缀的文件。");
      } else {
        //const reader = new FileReader();
        //reader.readAsDataURL(file);
        //reader.onload = () => {
        //  this.form.imgUrl = reader.result;
        //};
      }
    },
    quillImgSuccess(res, file) {
      // res为图片服务器返回的数据
      // 如果上传成功
      if (res.code == 200) {
        // 获取光标所在位置
        // 插入图片  res.url为服务器返回的图片地址
        this.form.imgUrl =res.url;

      } else {
        this.$message.error("图片插入失败");
      }
    },
  }
};
</script>
