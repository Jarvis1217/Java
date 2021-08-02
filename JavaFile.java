     public void test() {
        try {
            // 写文件
            File file = new File("C:\\Users\\test");
//          FileWriter writer = new FileWriter(file);//不追加
            FileWriter writer = new FileWriter(file, true);//追加
            BufferedWriter bw = new BufferedWriter(writer);
            bw.write("test?");
            bw.newLine();
            bw.close();
            writer.close();
            
            // 读文件
            FileReader reader = new FileReader("C:\\Users\\test");
            BufferedReader br = new BufferedReader(reader);
            String templine = null;
            while ((templine = br.readLine()) != null) {
                System.out.println(templine);
            }
            br.close();
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }