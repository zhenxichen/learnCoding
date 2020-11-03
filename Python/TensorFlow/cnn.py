# 使用TensorFlow实现CNN，并用MNIST数据集验证

import tensorflow as tf
import matplotlib.pyplot as plt

# 引用模块
datasets = tf.keras.datasets
mnist = datasets.mnist
layers = tf.keras.layers
models = tf.keras.models

def load_mnist():
	data_path = 'D:\\datasets\\mnist.npz'	# 数据集存储路径
	(train_images, train_labels), (test_images, test_labels) =\
		mnist.load_data(path= data_path)
	train_images = train_images.reshape((60000, 28, 28, 1))
	test_images = test_images.reshape((10000, 28, 28, 1))
	# 将像素的值标准化至0到1的区间内
	train_images, test_images = train_images / 255.0, test_images / 255.0
	return train_images, train_labels, test_images, test_labels

# 显示前25个图像和标签
# 用于验证数据是否已正确导入
def verify_data(train_images, train_labels):
	plt.figure(figsize= (10, 10))
	for i in range(25):
		plt.subplot(5, 5, i + 1)
		plt.xticks([])
		plt.yticks([])
		plt.grid(False)
		plt.imshow(train_images[i], cmap= plt.cm.binary)
		plt.xlabel(train_labels[i])
	plt.show()

def create_cnn():
	model = models.Sequential()
	model.add(layers.Conv2D(32, (3, 3), activation='relu', input_shape=(28, 28, 1)))
	model.add(layers.MaxPooling2D((2, 2)))
	model.add(layers.Conv2D(64, (3, 3), activation='relu'))
	model.add(layers.MaxPooling2D((2, 2)))
	model.add(layers.Conv2D(64, (3, 3), activation='relu'))
	# print(model.summary())
	model.add(layers.Flatten())
	model.add(layers.Dense(64, activation='relu'))
	model.add(layers.Dense(10))
	return model

# 评估模型
def evaluate(history):
	plt.plot(history.history['accuracy'], label='accuracy')
	plt.plot(history.history['val_accuracy'], label = 'val_accuracy')
	plt.xlabel('Epoch')
	plt.ylabel('Accuracy')
	plt.ylim([0.5, 1])
	plt.legend(loc='lower right')
	plt.show()

def main():
	train_images, train_labels, test_images, test_labels = load_mnist()
	# verify_data(train_images, train_labels)
	model = create_cnn()
	model.compile(optimizer='adam', \
		loss=tf.keras.losses.SparseCategoricalCrossentropy(from_logits=True), \
		metrics=['accuracy'])
	history = model.fit(train_images, train_labels, epochs=10, 
					validation_data=(test_images, test_labels))
	evaluate(history)
	test_loss, test_acc = model.evaluate(test_images,  test_labels, verbose=2)
	print(test_acc)
	

if __name__ == "__main__":
	main()