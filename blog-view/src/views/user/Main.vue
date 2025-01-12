<template>
    <div>
        <div
            style="padding: 20px 30px;position: fixed;top: 0;z-index: 1000;background-color: #fff;width: 100%;border-bottom: 1px solid #f1f1f1;">
            <Logo name="我的博客" bag="#333" />
            <span style="position: fixed;right: 20px;top: 15px;display: flex;align-items: center;">
                <img :src="userInfo.userAvatar" alt="设置头像" @click="avatarSet"
                    style="width: 40px;height: 40px;border-radius: 20px;" />
                <span style="margin-left: 5px;">{{ userInfo.userName }}</span>
                <span @click="loginOut" style="margin-left: 20px;">退出登录</span>
            </span>
        </div>
        <div style="padding: 100px 30px;">
            <el-row>
                <el-col :span="4">
                    <div style="margin:  0 20px 20px 0;">
                        <span class="create-btn" @click="createBlog">
                            <i class="el-icon-edit"></i>
                            创作内容
                        </span>
                    </div>
                    <div style="margin-bottom: 10px;margin-right: 20px;">
                        <input v-model="blogQueryDto.title" style="width: 60%;" class="title" placeholder="标题" />
                        <span class="save-btn" style="margin-top: 5px;" @click="searchBlog">搜索</span>
                    </div>
                    <div @click="blogClick(blog)" class="blog-item" v-for="(blog, index) in blogs" :key="index">
                        <h3 style="margin: 10px 0;">{{ blog.title }}</h3>
                        <div style="font-size: 14px;">
                            <span>{{ timeParse(blog.createTime) }}</span>
                            <span style="margin-left: 10px;">字数{{ blog.frontNum }}</span>
                            <el-dropdown style="float: right;">
                                <span>
                                    <i class="el-icon-more-outline"></i>
                                </span>
                                <el-dropdown-menu slot="dropdown">
                                    <el-dropdown-item @click.native="blogEdit(blog)"
                                        icon="el-icon-edit-outline">编辑</el-dropdown-item>
                                    <el-dropdown-item @click.native="blogDelete(blog)"
                                        icon="el-icon-delete-solid">删除</el-dropdown-item>
                                </el-dropdown-menu>
                            </el-dropdown>
                        </div>
                    </div>
                </el-col>
                <el-col :span="20">
                    <div v-if="showFlag">
                        <div style="padding: 0 50px;">
                            <div style="font-size: 36px;font-weight: bold;">
                                {{ showBlog.title }}
                            </div>
                            <div style="margin: 10px 0;">
                                <span>{{ timeParse(showBlog.createTime) }}</span>
                                <span style="margin-left: 10px;">字数{{ showBlog.frontNum }}</span>
                            </div>
                            <div v-html="showBlog.content"></div>
                        </div>
                    </div>
                    <div v-else>
                        <div>
                            <input v-model="editBlog.title" class="title" placeholder="标题" />
                            <span class="save-btn" @click="saveBlog">确定保存</span>
                        </div>
                        <div>
                            <Editor height="calc(100vh - 400px)" :receiveContent="editBlog.content"
                                @on-receive="onReceive" />
                        </div>
                    </div>
                </el-col>
            </el-row>
        </div>
        <el-dialog :show-title="false" :show-close="false" :visible.sync="updateInfoDialog" width="30%">
            <div style="padding: 30px;">
                <span style="display: inline-block;font-size: 16px;">用户信息修改</span>
            </div>
            <div style="padding: 30px;">
                <div style="margin-left: 10px;">
                    <el-upload class="avatar-uploader" action="/api/blog/v1.0/file/upload" :show-file-list="false"
                        :on-success="handleAvatarSuccess">
                        <img v-if="userInfo.userAvatar" :src="userInfo.userAvatar" style="width: 80px;height: 80px;">
                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                    </el-upload>
                </div>
                <div style="margin: 10px 0;">
                    <input v-model="userInfo.userName" class="title" placeholder="用户名" />
                </div>

            </div>
            <span slot="footer" class="dialog-footer">
                <el-button size="mini" @click="updateInfoDialog = false">取 消</el-button>
                <el-button size="mini" @click="updateUserInfo">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>
<script>
import Logo from "@/components/Logo"
import Editor from "@/components/Editor"
import { timeAgo } from "@/utils/data"
import { clearToken } from "@/utils/storage"
export default {
    components: { Logo, Editor },
    name: "UserMain",
    data() {
        return {
            data: {},
            blogQueryDto: {},
            blogs: [],
            showFlag: false,
            operationFlag: false, // false: 新增；true： 修改
            showBlog: {},
            editBlog: {},
            userInfo: { userAvatar: '' },
            updateInfoDialog: false,
        }
    },
    created() {
        this.getUserInfo();
    },
    methods: {
        searchBlog() {
            this.queryBlogs();
        },
        // 博客删除
        async blogDelete(blog) {
            const confirmed = await this.$swalConfirm({
                title: '博客删除' + `【${blog.title}】`,
                text: `删除后数据不可回复`,
                icon: 'error',
            });
            if (confirmed) {
                const ids = [];
                ids.push(blog.id);
                this.$axios.post('/blog/batchDelete', ids).then(response => {
                    if (response.data.code === 200) {
                        this.$swal.fire({
                            title: '博客删除',
                            text: '博客删除成功',
                            icon: 'success',
                            showConfirmButton: false,
                            timer: 1000,
                        });
                        this.queryBlogs();
                    }
                })
            }
        },
        async loginOut() {
            const confirmed = await this.$swalConfirm({
                title: '退出登录',
                text: `退出后需要重新登录？`,
                icon: 'error',
            });
            if (confirmed) {
                // 先清除Token
                clearToken();
                this.$router.push('/login');
            }
        },
        handleAvatarSuccess(res, file) {
            if (res.code === 200) {
                this.userInfo.userAvatar = res.data;
            }
        },
        updateUserInfo() {
            this.$axios.put('/user/update', this.userInfo).then(response => {
                if (response.data.code === 200) {
                    this.updateInfoDialog = false;
                }
            })
        },
        avatarSet() {
            this.updateInfoDialog = true;
        },
        getUserInfo() {
            this.$axios.get('/user/auth').then(response => {
                if (response.data.code === 200) {
                    this.userInfo = response.data.data;
                    this.blogQueryDto.userId = this.userInfo.id;
                    this.queryBlogs();
                } else {
                    this.$router.push('/login');
                }
            }).catch(error => {
                console.error(error);
                // token校验失败，直接跳到登录页
                this.$router.push('/login');
            });
        },
        blogClick(blog) {
            this.showFlag = true;
            this.showBlog = blog;
        },
        blogEdit(blog) {
            this.showFlag = false;
            this.operationFlag = true;
            this.editBlog = blog;
        },
        timeParse(time) {
            return timeAgo(time);
        },
        createBlog() {
            this.showFlag = false;
            this.operationFlag = false;
            this.editBlog = {};
        },
        // 博客新增或修改
        saveBlog() {
            const api = this.operationFlag ? '/blog/update' : '/blog/save';
            this.$axios.post(api, this.editBlog).then(response => {
                if (response.data.code === 200) {
                    this.$swal.fire({
                        title: !this.operationFlag ? '博客新增' : '博客修改',
                        text: !this.operationFlag ? '博客新增成功' : '博客修改成功',
                        icon: 'success',
                        showConfirmButton: false,
                        timer: 1000,
                    });
                    this.queryBlogs();
                }
            });
        },
        onReceive(content) {
            this.editBlog.content = content;
        },
        // 加载全部的博客
        queryBlogs() {
            this.$axios.post('/blog/query', this.blogQueryDto).then(response => {
                if (response.data.code === 200) {
                    this.blogs = response.data.data;
                }
            });
        },
    }
};
</script>
<style scoped lang="scss">
.title {
    line-height: 50px;
    outline: none;
    border: none;
    font-size: 36px;
    font-weight: bold;
    padding: 0 10px;
    width: 80%;
}

.blog-item {
    cursor: pointer;
    padding: 10px;
    margin-right: 20px;
    border-radius: 5px;
}

.blog-item:hover {
    background-color: #f1f1f1;
}

.create-btn {
    display: inline-block;
    background-color: rgb(16, 170, 156);
    color: #fff;
    padding: 10px 0;
    border: none;
    border-radius: 5px;
    text-align: center;
    width: 100%;
    cursor: pointer;
}


.save-btn {
    display: inline-block;
    background-color: rgb(169, 211, 239);
    color: #ffffff;
    font-size: 14px;
    cursor: pointer;
    border-radius: 3px;
    padding: 10px 20px;
    float: right;
}

.save-btn:hover {
    background-color: rgb(153, 207, 242);
}
</style>