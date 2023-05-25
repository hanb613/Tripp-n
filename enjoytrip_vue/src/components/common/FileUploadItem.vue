<template>
  <div>
    <b-form-file
      accept="image/*"
      v-model="file"
      :state="Boolean(file)"
      placeholder="이미지 업로드"
      drop-placeholder="Drop file here..."
    ></b-form-file>
    <div class="mt-3">
      파일명: {{ file ? file.name : "" }}
      <b-button pill variant="success" v-if="file" @click="submit()">
        <b-icon icon="upload" font-scale="1" style="margin-right: 5px"></b-icon>
        <span class="font">올리기</span>
      </b-button>
    </div>
  </div>
</template>

<script>
import { uploadFile } from "@/api/attraction";
import { mapState } from "vuex";

const memberStore = "memberStore";
const attractionStore = "attractionStore";

export default {
  name: "FileUploadItem",
  components: {},
  data() {
    return {
      file: null,
    };
  },
  created() {
    // this.likeNo = this.$route.params.likeNo;
    console.log(this.$route);
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
    ...mapState(attractionStore, ["likeNo"]),
  },
  methods: {
    submit() {
      if (this.file) {
        // 파일이 선택되었을 때만 업로드 수행
        const formData = new FormData();
        formData.append("likeNo", this.likeNo);
        formData.append("file", this.file);

        uploadFile(
          formData,
          ({ data }) => {
            let msg = "등록 처리시 문제가 발생했습니다.";
            if (data === "success") {
              msg = "등록이 완료되었습니다.";
            }
            alert(msg);
            this.$router.go(0);
          },
          (error) => {
            console.log(error);
          }
        );
      }
    },
  },
};
</script>

<style scoped></style>
