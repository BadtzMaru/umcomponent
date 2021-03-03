import React, { Component } from 'react';
import { View, Text, TouchableOpacity } from 'react-native';
import AnalyticsUtil from './native/AnalyticsUtil';
import PushUtil from './native/PushUtil';

class App extends Component {
	componentDidMount() {
		AnalyticsUtil.onPageStart('App');
	}
	componentWillUnmount() {
		AnalyticsUtil.onPageEnd('App');
	}
	getDeviceToken = () => {
		PushUtil.addAlias('myname', 'username', (code) => {
			console.log('code: ', code);
		});
		PushUtil.deviceToken((deviceToken) => {
			console.log('deviceToken: ', deviceToken);
		});
	};
	onClickAd = () => {
		AnalyticsUtil.onEvent('click_ad');
	};
	profileSignInWithPUID = () => {
		AnalyticsUtil.profileSignInWithPUID('myname');
	};
	profileSignOff = () => {
		AnalyticsUtil.profileSignOff();
	};
	onEvent = () => {
		AnalyticsUtil.onEvent('event0');
	};
	onEventWithMap = () => {
		AnalyticsUtil.onEventWithMap('event2', { name: 'umeng', sex: 'man' });
	};
	render() {
		return (
			<View>
				<Text>App</Text>
				<TouchableOpacity onPress={this.onClickAd}>
					<Text>点击 广告 事件</Text>
				</TouchableOpacity>
				<TouchableOpacity onPress={this.profileSignInWithPUID}>
					<Text>用户登录</Text>
				</TouchableOpacity>
				<TouchableOpacity onPress={this.profileSignOff}>
					<Text>用户登出</Text>
				</TouchableOpacity>
				<TouchableOpacity onPress={this.onEvent}>
					<Text>普通事件</Text>
				</TouchableOpacity>
				<TouchableOpacity onPress={this.onEventWithMap}>
					<Text>多属性事件</Text>
				</TouchableOpacity>
				<TouchableOpacity onPress={this.getDeviceToken}>
					<Text>获取deviceToken</Text>
				</TouchableOpacity>
			</View>
		);
	}
}

export default App;
