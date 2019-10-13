<template>
  <div id="Loves">
    <div class="container">
      <h2>今日热点</h2>
      <el-carousel :interval="4000" type="card" height="200px">
        <el-carousel-item v-for="item in 6" :key="item">
          <h3 class="medium">{{ item }}</h3>
        </el-carousel-item>
      </el-carousel>

      <div class="block">
        <ul class="infinite-list" v-infinite-scroll="load" style="overflow:auto" infinite-scroll-delay="1000">
          <li v-for="hotNew in hotNews" class="infinite-list-item" >
            <el-timeline>
              <el-timeline-item timestamp="2018/4/12" placement="top">
                <el-card>
                  <h4>{{hotNew.title}} + {{count}}</h4>
                  <p>{{hotNew.user}} 发布于 2018/4/2 20:46</p>
                  <el-image :src="src">
                    <div slot="placeholder" class="image-slot">
                      图片加载中<span class="dot">...</span>
                    </div>
                  </el-image>
                </el-card>
              </el-timeline-item>
            </el-timeline>
          </li>
          <p v-if="loading">加载中...</p>
          <p v-if="noMore">没有更多了</p>
        </ul>

      </div>

    </div>
    <a class="btn btn-default" href="#" role="button" style="position:fixed;right:0;bottom:0">
      回到顶部
    </a>
  </div>
</template>

<script>
    export default {
        name: "Loves",
        data() {
            return {
                src: 'http://s.cn.bing.net/th?id=OHR.midmoon_ZH-CN4973736313_1920x1080.jpg&rf=LaDigue_1920x1080.jpg',
                count : 1,
                loading: false,
                hotNews:[{'title':'HelloWorld','user':'kid1999'},{'title':'HelloWorld','user':'kid1999'},{'title':'HelloWorld','user':'kid1999'}]
            }
        },
        computed: {
            noMore () {
                return this.count >= 20
            },
            disabled () {
                return this.loading || this.noMore
            }
        },
        methods:{
            load () {
                this.loading = true
                setTimeout(() => {
                    this.count += 1
                    this.hotNews.push({'title':'HelloWorld','user':'kid1999'});
                    this.loading = false
                }, 200)
            }
        }
    }
</script>

<style scoped>
  .wraper {
    height: 100vh;
    overflow-x: hidden;
  }

  ul li{
    list-style-type:none;
  }
  image{
    height: 200px;
    width: auto;
  }

  .el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 200px;
    margin: 0;
  }

  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }

  .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
  }
</style>
