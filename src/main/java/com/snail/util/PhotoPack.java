package com.snail.util;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.github.sarxos.webcam.WebcamUtils;
import com.github.sarxos.webcam.util.ImageUtils;
import com.snail.conf.CommonUrl;
@Component
public class PhotoPack{
	@Autowired
	private CommonUrl commonUrl=new CommonUrl();
    public  void registered(final String userName) throws IOException, InterruptedException{
    	final Webcam webcam = Webcam.getDefault();
        webcam.setViewSize(WebcamResolution.VGA.getSize());
        WebcamPanel panel = new WebcamPanel(webcam);
        panel.setImageSizeDisplayed(true);
        panel.setMirrored(true); 
        final JFrame window = new JFrame("人脸注册");
        Dimension   screensize   =   Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int)screensize.getWidth();
        int height = (int)screensize.getHeight();
        window.setLocation(width/2-320, height/2-205);
	    window.setUndecorated(true);
	    window.setOpacity((float)0.9);
	    window.setAlwaysOnTop(true);
        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e)
            {
                webcam.close();
                window.dispose();
            }
        });
        final JButton button = new JButton("注册");
        window.add(panel, BorderLayout.CENTER);
        window.add(button, BorderLayout.SOUTH);
        window.setResizable(true);
        window.pack();
        window.setVisible(true);
        button.setVisible(false);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                button.setEnabled(false);
                String fileName =  commonUrl.getFileUpLoadURL() + userName;
                File file = new File(fileName);
				if (file.exists()) {
					if (!file.delete()) {
						return;
					};
				}
                
                WebcamUtils.capture(webcam, fileName, ImageUtils.FORMAT_PNG);
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                    	window.setAlwaysOnTop(false);
                        button.setEnabled(true);
                        webcam.close();
                        window.dispose();
                    }
                });
                
            }
        });
        TimeUnit.SECONDS.sleep(3);
        button.doClick();
        webcam.close();
        window.dispose();
    }
}