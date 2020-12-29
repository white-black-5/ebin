<template>
    <div>
        <el-row :gutter="20" class="mgb20">
            <el-col :span="6">
                <el-card>
                    <div class="grid-content">
                        <div class="grid-content-center">
                            <div class="grid-num">{{consumerCount}}</div>
                            <div>用户总数</div>
                        </div>
                    </div>
                </el-card>
            </el-col>
            <el-col :span="6">
                <el-card>
                    <div class="grid-content">
                        <div class="grid-content-center">
                            <div class="grid-num">{{garbageCount}}</div>
                            <div>入库垃圾数量</div>
                        </div>
                    </div>
                </el-card>
            </el-col>
            <el-col :span="6">
                <el-card>
                    <div class="grid-content">
                        <div class="grid-content-center">
                            <div class="grid-num">{{searchCount}}</div>
                            <div>总查询数</div>
                        </div>
                    </div>
                </el-card>
            </el-col>
            <el-col :span="6">
                <el-card>
                    <div class="grid-content">
                        <div class="grid-content-center">
                            <div class="grid-num">{{adminCount}}</div>
                            <div>管理员数量</div>
                        </div>
                    </div>
                </el-card>
            </el-col>
        </el-row>
        <el-row :gutter="20" class="mgb20">
            <el-col :span="12">
                <h3 class="mgb20">用户性别比例</h3>
                <div style="background-color:white">
                    <ve-pie :data="consumerSex"></ve-pie>
                </div>
            </el-col>
            <el-col :span="12">
                <h3 class="mgb20">入库垃圾数量信息</h3>
                <div style="background-color:white">
                    <ve-histogram :data="garbageType"></ve-histogram>
                </div>
            </el-col>
        </el-row>
        <el-row :gutter="20" class="mgb20">
            <el-col :span="12">
                <h3 class="mgb20">用户性别比例</h3>
                <div style="background-color:white">
                    <ve-histogram :data="consumerSex"></ve-histogram>
                </div>
            </el-col>
            <el-col :span="12">
                <h3 class="mgb20">入库垃圾数量信息</h3>
                <div style="background-color:white">
                    <ve-pie :data="garbageType"></ve-pie>
                </div>
            </el-col>
        </el-row>
    </div>
</template>

<script>
import {getAllUser, getAllAdmin,getAllGarbage,delUser} from '../api/index';
export default {
    data(){
        return{
            consumerCount:0,    //用户总数
            garbageCount:0,     //入库垃圾数量
            searchCount:0,      //总查询数
            adminCount:0,       //管理员数
            consumer:[],         //所有用户
            admin:[],            //所有管理员
            garbage:[],          //所有垃圾信息
            consumerSex:{
                columns:['性别','总数'],
                rows:[
                    {'性别': '男','总数': 0},
                    {'性别': '女','总数': 0}
                ]
            },
            options: {
                color: ['#87cefa','#ffc0cb']
            },
            options1: {
                color: ['yellow']
            },
            garbageType:{           //垃圾种类
                columns:['种类','总数'],
                rows:[
                    {'种类': '可回收垃圾','总数': 0},
                    {'种类': '厨余垃圾','总数': 0},
                    {'种类': '有害垃圾','总数': 0},
                    {'种类': '其他垃圾','总数': 0},
                ]
            }
        }
    },
    created(){

    },
    mounted(){
        this.getUser();
        this.getAdmin();
        this.getGarbage();
    },
    methods:{
        getUser(){
            getAllUser().then(res => {
                this.consumer=res;
                this.consumerCount=res.length;
                this.consumerSex.rows[0]['总数'] = this.setSex(1,this.consumer);
                this.consumerSex.rows[1]['总数'] = this.setSex(0,this.consumer);
                
            })
        },
        setSex(sex,consumer){               //根据性别获取用户数
            let count=0;
            for(let item of consumer){
                if(sex==item.sex){
                    count++;
                }
            }
            return count;
        },
        getAdmin(){
            getAllAdmin().then(res => {
                this.admin=res;
                this.adminCount=res.length;
                this.searchCount=this.getSearchTimes(this.consumer);
                

            })
        },
        getGarbage(){
            getAllGarbage().then(res => {
                this.garbage=res;
                this.garbageCount=res.length;
                for(let items of res){
                    this.getByType(items.type);
                }
            })
        },
        getSearchTimes(consumer){
            let count=0;
            for(let item of consumer){
                count+=item.times;
            }
            return count;
        },
        getByType(type){               //根据垃圾种类获取数量
            
            for(let item of this.garbageType.rows){
                if(type.includes(item['种类'])){
                    item['总数']++;
                }
            }
        },
    }
}
</script>

<style scoped>
.grid-content{
    display: flex;
    align-items: center;
    height: 50px;
}
.grid-content-center{
    flex: 1;
    text-align: center;
    font-size: 14px;
    color: darkgray;
}
.grid-num{
    font-size: 30px;
    font-weight: bold;
}
</style>