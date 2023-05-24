<template>
  <div>
    <b-form-file
      accept="image/*"
      v-model="file"
      :state="Boolean(file)"
      placeholder="이미지 업로드"
      drop-placeholder="Drop file here..."
    ></b-form-file>
    <div class="mt-3">파일명: {{ file ? file.name : "" }}</div>
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
  methods: {},
};
</script>

<style scoped></style>