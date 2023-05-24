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
export default {
  name: "FileUploadItem",
  components: {},
  data() {
    return {
      file: null,
    };
  },
  created() {},
  methods: {
    submit() {
      console.log("file을 올립니다");
    },
  },
};
</script>

<style scoped></style>