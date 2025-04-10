
import java.util.Objects;


public class Emp {

 private int eid;
 private String ename;
 private String desig;
 private double sal;

 public Emp() {
  super();
// TODO Auto-generated constructor stub
 }

 public Emp(String ename, double sal, String desig) {
  super();

  this.ename = ename;
  this.desig = desig;
  this.sal = sal;
 }

 public int getEid() {
  return eid;
 }

 public void setEid(int eid) {
  this.eid = eid;
 }

 public String getEname() {
  return ename;
 }

 public void setEname(String ename) {
  this.ename = ename;
 }

 public String getDesig() {
  return desig;
 }

 public void setDesig(String desig) {
  this.desig = desig;
 }

 public double getSal() {
  return sal;
 }

 public void setSal(double sal) {
  this.sal = sal;
 }

 @Override
 public int hashCode() {
  return Objects.hash(desig, eid, ename, sal);
 }

 @Override
 public boolean equals(Object obj) {
  if (this == obj)
   return true;
  if (obj == null)
   return false;
  if (getClass() != obj.getClass())
   return false;
  Emp other = (Emp) obj;
  return Objects.equals(desig, other.desig) && eid == other.eid && Objects.equals(ename, other.ename)
    && Double.doubleToLongBits(sal) == Double.doubleToLongBits(other.sal);
 }

 @Override
 public String toString() {
  return "Emp [eid=" + eid + ", ename=" + ename + ", desig=" + desig + ", sal=" + sal + "]";
 }

}