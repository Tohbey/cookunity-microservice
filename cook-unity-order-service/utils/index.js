const RandExp = require("randexp");


GenerateCode = (num) => {
  const token = new RandExp(`[a-z]{${num}}`).gen();

  return token;
};

GenerateOTP = (num) => {
  const OTPCode = new RandExp(`[0-9]{${num}}`).gen();

  return OTPCode;
};

const paginate = (req) => {
  const page =
    typeof req.query.page !== "undefined" ? Math.abs(req.query.page) : 1;
  const pageSize =
    typeof req.query.pageSize !== "undefined"
      ? Math.abs(req.query.pageSize)
      : 50;
  const skip = (page - 1) * pageSize;

  return { page, pageSize, skip };
};


module.exports = {
  paginate,
  GenerateCode,
  GenerateOTP
};