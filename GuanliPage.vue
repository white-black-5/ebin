<template>
    <div class="table">
        <div class="container">
            <div class="handle-box">
                <el-input v-model="select_word" size="mini" placeholder="请输入管理员" class="handle-input"></el-input>
                <el-button type="primary" size="mini" @click="centerDialogVisible=true">添加管理员</el-button>
            </div>
        </div>
        <el-table size="mini" border style="width:100%" height="500px" :data="data">
            <el-table-column label="管理员" width="110" align="center">
                <template slot-scope="scope">
                    <div class="Admin-img">
                        <img :src="getUrl(scope.row.pic)" style="width:100%"/>
                    </div>
                    <el-upload :action="uploadUrl(scope.row.id)" :before-upload="beforeAvatorUpload" 
                        :on-success="handleAvatorSuccess">
                        <el-button size="mini">更新图片</el-button>
                    </el-upload>
                </template>
            </el-table-column>
            <el-table-column prop="name" label="管理员名" width="120" align="center"></el-table-column>
            <el-table-column prop="location" label="地区" width="120" align="center"></el-table-column>
            <el-table-column label="简介">
                <template slot-scope="scope">
                    <p style="height:100px; overflow:scroll">
                        {{scope.row.introduction}}
                    </p>
                </template>
            </el-table-column>
            <el-table-column label="操作" width="150" align="center">
                <template slot-scope="scope">
                    <el-button size="mini" @click="handleEdit(scope.row)">编辑</el-button>
                    <el-button size="mini" type="danger" @click="handleDelete(scope.row.id)">删除</el-button>
                    
                </template>
            </el-table-column>

        </el-table>


        <div class="pagination">
            <el-pagination
                background
                Layout = "total,prev,pager,next"
                :current-page="currentPage"
                :page-size="pageSize"
                :total="tableData.length"
                @current-change="handleCurrentChange"
                >
            </el-pagination>
        </div>
        <el-dialog title="添加管理员" :visible.sync="centerDialogVisible" width="400px" center>
            <el-form :model="registerForm" ref="registerForm" label-width="80px">
                <el-form-item prop="name" label="管理员" size="mini">
                    <el-input v-model="registerForm.name" placeholder="管理员"></el-input>
                </el-form-item>
                <el-form-item prop="username" label="用户名" size="mini">
                    <el-input v-model="registerForm.username" placeholder="用户名"></el-input>
                    
                </el-form-item>
                <el-form-item prop="password" label="密码" size="mini">
                    <el-input v-model="registerForm.password" placeholder="密码"></el-input>
                </el-form-item>

                <el-form-item prop="location" label="地区" size="mini">
                    <el-input v-model="registerForm.location" placeholder="地区"></el-input>
                </el-form-item>
                <el-form-item prop="introduction" label="简介" size="mini">
                    <el-input v-model="registerForm.introduction" placeholder="简介" type="textarea"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer">
                <el-button size="mini" @click="centerDialogVisible = false">取消</el-button>
                <el-button size="mini" @click="addAdmin">确定</el-button>
            </span>
        </el-dialog>

        <el-dialog title="修改挂你有" :visible.sync="editVisible" width="400px" center>
            <el-form :model="form" ref="form" label-width="80px">
                <el-form-item prop="name" label="管理员" size="mini">
                    <el-input v-model="form.name" placeholder="管理员"></el-input>
                </el-form-item>
                <el-form-item prop="username" label="用户名" size="mini">
                    <el-input v-model="form.username" placeholder="用户名"></el-input>
                </el-form-item>
                <el-form-item prop="password" label="密码" size="mini">
                    <el-input v-model="form.password" placeholder="密码"></el-input>
                </el-form-item>

                <el-form-item prop="location" label="地区" size="mini">
                    <el-input v-model="form.location" placeholder="地区"></el-input>
                </el-form-item>
                <el-form-item prop="introduction" label="简介" size="mini">
                    <el-input v-model="form.introduction" placeholder="简介" type="textarea"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer">
                <el-button size="mini" @click="editVisible = false">取消</el-button>
                <el-button size="mini" @click="editSave">确定</el-button>
            </span>
        </el-dialog>

        <el-dialog title="删除管理员" :visible.sync="delVisible" width="300px" center>
            <div align="center">删除不可恢复，是否删除？</div>
            <span slot="footer">
                <el-button size="mini" @click="delVisible = false">取消</el-button>
                <el-button size="mini" @click="deleRow">确定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
import {getAllAdmin, setAdmin,updateAdmin,delAdmin} from '../api/index';
import { mixin } from '../mixins';
export default {
    mixins: [mixin],
    data(){
        return{
            centerDialogVisible: false, //添加弹窗是否显示
            editVisible:false,          //编辑弹窗是否显示
            delVisible:false,           //删除弹出是否显示
            registerForm:{
                name: '',
                username: '',
                password: '',
                location: '',
                introduction: ''
            },
            form:{
                id:'',
                name: '',
                username: '',
                password: '',
                location: '',
                introduction: ''
            },
            tableData:[],
            tempData:[],
            select_word:'',
            pageSize: 5,    //分页每页大小
            currentPage: 1,  //当前页
            idx :-1           //当前选择项
        }
    },
    computed:{
        //计算当前搜索结果表里的数据
        data(){
            return this.tableData.slice((this.currentPage - 1)*this.pageSize,this.currentPage*this.pageSize)
        }
    },
    watch:{
        //搜索框里面内容发生变化，搜索结果table结果跟着内容变化
        select_word: function(){
            if(this.select_word == ''){
                this.tableData = this.tempData;
            }else{
                this.tableData = [];
                for(let item of this.tempData){
                    if(item.name.includes(this.select_word)){
                        this.tableData.push(item);
                    }
                }
            }
        }
        
    },
    created(){
        this.getData();
    },
    methods:{
        //获取当前页
        handleCurrentChange(val){
            this.currentPage=val;
        },
        //查询所有管理员
        getData(){
            this.tempData = [];
            this.tableData = [];
            getAllAdmin().then(res => {
                this.tempData = res;
                this.tableData = res;
            })
        },
        //添加管理员
        addAdmin(){
            
            let params = new URLSearchParams();
            params.append('name',this.registerForm.name);
            params.append('username',this.registerForm.username);
            params.append('pic','/img/AdminPic/head.png');
            params.append('password',this.registerForm.password);
            params.append('location',this.registerForm.location);
            params.append('introduction',this.registerForm.introduction);
            setAdmin(params)
            .then(res => {
                if(res.code == 1){
                    this.getData();
                    this.notify("添加成功","success");
                }else{
                    this.notify("添加失败","error");
                }
            })
            .catch(err =>{
                console.log(err);
            });
            this.centerDialogVisible=false;

        },
        //弹出编辑页面
        handleEdit(row){
            this.editVisible=true;
            this.form={
                id: row.id,
                name: row.name,
                username: row.username,
                password: row.password,
                location: row.location,
                introduction: row.introduction
            }
        },
        //保存编辑页面修改的数据
        editSave(){
            
            let params = new URLSearchParams();
            
            params.append('id',this.form.id);
            params.append('name',this.form.name);
            params.append('username',this.form.username);
            params.append('password',this.form.password);
            params.append('location',this.form.location);
            params.append('introduction',this.form.introduction);

            updateAdmin(params)
            .then(res => {
                if(res.code == 1){
                    this.getData();
                    this.notify("修改成功","success");
                }else{
                    this.notify("修改失败","error");
                }
            })
            .catch(err =>{
                console.log(err);
            });
            this.editVisible=false;
        },
        //更新图片
        uploadUrl(id){
            return `${this.$store.state.HOST}/admin/updateAdminPic?id=${id}`
        },
        //删除一名歌手
        deleRow(){
            delAdmin(this.idx)
            .then(res => {
                if(res){
                    this.getData();
                    this.notify("删除成功","success");
                }else{
                    this.notify("删除失败","error");
                }
            })
            .catch(err =>{
                console.log(err);
            });
            this.delVisible=false;
        },
    }
}
</script>

<style scoped>
    .handle-box{
        margin-bottom: 20px;
    }
    .Admin-img{
        width: 100%;
        height:90px;
        border-radius: 5px;
        margin-bottom: 5px;
        overflow: hidden;
        
    }
    .handle-input{
        width: 300px;
        display: inline-block;
    }
    .pagination{
        display: flex;
        justify-content: center;
    }
</style>