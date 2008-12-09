/*
 * SIP Communicator, the OpenSource Java VoIP and Instant Messaging client.
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package net.java.sip.communicator.impl.gui.main.chatroomslist;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import net.java.sip.communicator.impl.gui.*;
import net.java.sip.communicator.impl.gui.main.chat.conference.*;
import net.java.sip.communicator.impl.gui.main.chatroomslist.createforms.*;
import net.java.sip.communicator.impl.gui.main.chatroomslist.joinforms.*;
import net.java.sip.communicator.impl.gui.utils.*;

/**
 * The <tt>ChatRoomsListRightButtonMenu</tt> is the menu, opened when user clicks
 * with the right mouse button on the chat rooms list panel. It's the one that
 * contains the create chat room item.
 *
 * @author Yana Stamcheva
 */
public class ChatRoomServerRightButtonMenu
    extends JPopupMenu
    implements  ActionListener
{
    private JMenuItem createChatRoomItem = new JMenuItem(
        GuiActivator.getResources().getI18NString("service.gui.CREATE_CHAT_ROOM"),
        new ImageIcon(ImageLoader.getImage(ImageLoader.CHAT_ROOM_16x16_ICON)));

    private JMenuItem joinChannelItem = new JMenuItem(
        GuiActivator.getResources().getI18NString("service.gui.JOIN_CHAT_ROOM"),
        new ImageIcon(ImageLoader.getImage(ImageLoader.SEARCH_ICON_16x16)));

    private ChatRoomProviderWrapper chatRoomProvider;

    /**
     * Creates an instance of <tt>ChatRoomsListRightButtonMenu</tt>.
     * 
     * @param mainFrame the main application window
     * @param protocolProvider the protocol provider corresponding to the multi
     * user chat server
     */
    public ChatRoomServerRightButtonMenu(
        ChatRoomProviderWrapper chatRoomProvider)
    {
        super();

        this.chatRoomProvider = chatRoomProvider;

        this.setLocation(getLocation());

        this.init();
    }

    /**
     * Initializes the menu, by adding all containing menu items.
     */
    private void init()
    {
        this.add(createChatRoomItem);
        this.add(joinChannelItem);
        
        this.createChatRoomItem.setName("createChatRoom");
        this.joinChannelItem.setName("joinChatRoom");
        
        this.createChatRoomItem.setMnemonic(
            GuiActivator.getResources()
                .getI18nMnemonic("service.gui.CREATE_CHAT_ROOM"));
        this.joinChannelItem.setMnemonic(
            GuiActivator.getResources()
                .getI18nMnemonic("service.gui.JOIN_CHAT_ROOM"));
        
        this.createChatRoomItem.addActionListener(this);
        this.joinChannelItem.addActionListener(this);
    }
    
    /**
     * Handles the <tt>ActionEvent</tt>. Determines which menu item was
     * selected and makes the appropriate operations.
     */
    public void actionPerformed(ActionEvent e)
    {
        JMenuItem menuItem = (JMenuItem) e.getSource();
        String itemName = menuItem.getName();

        if (itemName.equals("createChatRoom"))
        {
            CreateChatRoomDialog createChatRoomDialog
                = new CreateChatRoomDialog(chatRoomProvider);

            createChatRoomDialog.setVisible(true);
        }
        else if (itemName.equals("joinChatRoom"))
        {
            JoinChatRoomDialog joinChannelDialog
                = new JoinChatRoomDialog(chatRoomProvider);

            joinChannelDialog.pack();
            joinChannelDialog.setLocation(
                Toolkit.getDefaultToolkit().getScreenSize().width/2
                    - joinChannelDialog.getWidth()/2,
                Toolkit.getDefaultToolkit().getScreenSize().height/2
                    - joinChannelDialog.getHeight()/2
                );
            joinChannelDialog.showDialog();
        }
    }
}
